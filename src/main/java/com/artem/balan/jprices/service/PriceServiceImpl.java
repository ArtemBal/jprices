package com.artem.balan.jprices.service;

import com.artem.balan.jprices.model.Offer;
import com.artem.balan.jprices.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository repository;

    @Override
    public void save(Offer offer) {
        repository.save(offer);
    }

    @Override
    public List<Offer> getAllOffers() {
        return repository.findAll();
    }
}