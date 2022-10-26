package com.kodilla.rental.client;

import com.kodilla.rental.config.NbpConfig;
import com.kodilla.rental.domain.dto.api.nbp.NbpResponseDto;
import com.kodilla.rental.domain.enums.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class NbpApiClient {

    @Autowired
    private NbpConfig nbpConfig;

    @Autowired
    private RestTemplate restTemplate;

    public double getExchangeRate(Currency currency) {

        if (currency.equals("PLN")) {
            return 1;
        } else {
            URI uri = UriComponentsBuilder.fromHttpUrl(nbpConfig.getNbpApiEndpoint() + currency)
                    .build()
                    .encode()
                    .toUri();

            NbpResponseDto nbpResponseDto = restTemplate.getForObject(uri, NbpResponseDto.class);
            return nbpResponseDto.getRates().get(0).getMid();
        }
    }
}
