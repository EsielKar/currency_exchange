package com.esielkar.currency_exchange.service;


import com.esielkar.currency_exchange.exception.CurrencyNotFoundException;
import com.esielkar.currency_exchange.exception.NegativeValueException;
import com.esielkar.currency_exchange.model.Currency;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyExchangeService {

    private Map<String, Currency> currencies;

    public CurrencyExchangeService() {
        currencies = new HashMap<>();
        currencies.put("MXN", new Currency("MXN", 0.0561));
        currencies.put("USD", new Currency("USD", 1));
        currencies.put("EUR", new Currency("EUR", 1.07352));

    }
    public List<Currency> getAll() {
        return new LinkedList<>(currencies.values());
    }

    public Map<String, Double> getCurrencyExchangesOf(String currency) throws CurrencyNotFoundException {
        return getCurrencyExchangesOf(currency, 1);
    }

    public Map<String, Double> getCurrencyExchangesOf(String currency, double value) throws CurrencyNotFoundException, NegativeValueException {
        currency = currency.toUpperCase();
        if (value >= 0.0) {
            if (currencies.containsKey(currency)) {
                Map<String, Double> currencyExchanges = new HashMap<>();
                double valueInDollars = value * currencies.get(currency).getValue(); // Valor en dólares
                for (String key : currencies.keySet()) {
                    if (key.equals(currency)) continue;
                    //Valor en la moneda a convertir
                    double finalValue = valueInDollars * currencies.get(key).getCurrencyValue();
                    currencyExchanges.put(key, finalValue);
                }
                return currencyExchanges;
            } else {
                throw new CurrencyNotFoundException();
            }
        }
        throw  new NegativeValueException();
    }




}
