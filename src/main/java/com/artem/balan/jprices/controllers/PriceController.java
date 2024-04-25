package com.artem.balan.jprices.controllers;

import com.artem.balan.jprices.model.Offer;
import com.artem.balan.jprices.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/prices")
    public List<Offer> getAllOffers() {
        return priceService.getAllOffers();
    }
}
