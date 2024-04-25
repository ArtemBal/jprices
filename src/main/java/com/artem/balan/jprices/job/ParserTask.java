package com.artem.balan.jprices.job;

import com.artem.balan.jprices.model.Offer;
import com.artem.balan.jprices.service.PriceService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ParserTask {

    @Autowired
    private PriceService priceService;

    @Scheduled(fixedDelay = 50_000_0) // 500s
    public void parsePrices() {
        String siteUrlA = "https://agrotender.com.ua/traders/region_vinnica/soya";
        String siteUrlB = "https://tripoli.land/ua/analytics/soya";
        try {
            parsePricesA(siteUrlA);
            parsePricesB(siteUrlB);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // agrotender.com.ua
    public void parsePricesA(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Element table = doc.select("table").get(0).select("tbody").get(0);
        Elements trs = table.select("tr");
        for (Element row : trs) {
            Elements tds = row.select("td");

            Offer offer = new Offer();
            offer.setCrop("соя");
            offer.setPrice(tds.get(1).text());
            offer.setSource("agrotender.com.ua");
            priceService.save(offer);
        }
    }

    // tripoli.land
    public void parsePricesB(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Element table = doc.select("table").get(0).select("tbody").get(0);
        Elements trs = table.select("tr");
        Offer offer = new Offer();
        offer.setCrop("соя");
        offer.setPrice(trs.get(7).select("td").get(1).text());
        offer.setSource("tripoli.land");
        priceService.save(offer);
    }
}
