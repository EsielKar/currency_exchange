package com.esielkar.currency_exchange.controller;

import com.esielkar.currency_exchange.exception.CurrencyNotFoundException;
import com.esielkar.currency_exchange.exception.NegativeValueException;
import com.esielkar.currency_exchange.model.Currency;
import com.esielkar.currency_exchange.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("currencies")
public class CurrencyExchangeController {
    private CurrencyExchangeService currencyExchangeService;
    @Autowired
    public CurrencyExchangeController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<String> getCurrencies() {
        return  currencyExchangeService.getAll().stream().map(Currency::getId).toList();
    }

    @GetMapping("{currency}")
    public Map<String, Double> getCurrencyExchangesOf(@PathVariable("currency") String currency) throws CurrencyNotFoundException {
        return currencyExchangeService.getCurrencyExchangesOf(currency);
    }

    @GetMapping("{currency}/exchange/{value}")
    public Map<String, Double> getCurrencyExchangesOfValue(@PathVariable("currency") String currency, @PathVariable("value") double value) throws CurrencyNotFoundException, NegativeValueException {
        return currencyExchangeService.getCurrencyExchangesOf(currency, value);
    }
}
