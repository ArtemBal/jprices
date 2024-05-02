package com.artem.balan.jprices.service;

import com.artem.balan.jprices.model.Offer;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface PriceService {
    public void save(Offer offer);
    public boolean isExist(String source, LocalDate date);
    public List<Offer> getAllOffers();
}
