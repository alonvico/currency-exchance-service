package com.in28minutes.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    private Environment environment;
    private CurrencyExchangeRepository repository;

    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        //CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));

        logger.info("retrieveExchangeValue called with {} to {}", from, to);

        Optional<CurrencyExchange> currencyExchangeOptional = repository.findByFromAndTo(from, to);
        if(currencyExchangeOptional.isEmpty()) {
            throw new CurrencyExchangeNotFound("From:"+from+", To:"+to);
        } else {
            CurrencyExchange currencyExchange = currencyExchangeOptional.get();

            String port = environment.getProperty("local.server.port");

            //Kubernetes
            String host = environment.getProperty("HOSTNAME");
            String version = "v1.0";

            currencyExchange.setEnvironment(port+" "+version+" "+host);
            return currencyExchange;
        }

    }
}
