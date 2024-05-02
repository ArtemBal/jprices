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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ParserTask {

    @Autowired
    private PriceService priceService;

    @Scheduled(fixedDelay = 20_000) // 20s
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
        String source = "agrotender.com.ua";
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.79 Safari/537.36")
                .referrer("http://www.google.com")
                .get();
        Element table = doc.select("table")
                .get(0)
                .select("tbody")
                .get(0);
        Elements trs = table.select("tr");
        for (Element row : trs) {
            Elements tds = row.select("td");
            String stringDate = tds.get(3).getElementsByClass("hidden_date").text();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(stringDate, formatter);
            if (priceService.isExist(source, date))
                continue;
            Offer offer = new Offer();
            offer.setCrop("соя");
            offer.setPrice(tds.get(1).text());
            offer.setSource(source);
            offer.setCreateDate(date);
            priceService.save(offer);
        }
    }

    // tripoli.land
    public void parsePricesB(String url) throws IOException {
        String source = "tripoli.land";
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.79 Safari/537.36")
                .referrer("http://www.google.com")
                .get();
        Element table = doc.select("table")
                .get(0)
                .select("tbody")
                .get(0);
        Elements trs = table.select("tr");
        LocalDate date = LocalDate.now();
        if (!priceService.isExist(source, date)) {
            Offer offer = new Offer();
            offer.setCrop("соя");
            offer.setPrice(trs.get(7).select("td").get(1).text());
            offer.setSource(source);
            offer.setCreateDate(LocalDate.now());
            priceService.save(offer);
        }
    }
}
