package com.artem.balan.jprices.controllers;

import com.artem.balan.jprices.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/prices")
    public String getAllOffers(Model model) {
        model.addAttribute("offers", priceService.getAllOffers());
        return "prices.html";
    }
}
