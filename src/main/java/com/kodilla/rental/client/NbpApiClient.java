package com.kodilla.rental.client;

import com.kodilla.rental.config.NbpConfig;
import com.kodilla.rental.domain.dto.api.nbp.NbpResponseDto;
import com.kodilla.rental.domain.enums.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class NbpApiClient {

    private final NbpConfig nbpConfig;

    private final RestTemplate restTemplate;

    public double getExchangeRate(Currency currency) {

        if (currency.equals(Currency.PLN)) {
            return 1;
        } else {
            URI uri = UriComponentsBuilder.fromHttpUrl(nbpConfig.getNbpApiEndpoint() + currency)
                    .queryParam("format", "json")
                    .build()
                    .encode()
                    .toUri();

            NbpResponseDto nbpResponseDto = restTemplate.getForObject(uri, NbpResponseDto.class);
            assert nbpResponseDto != null;
            return nbpResponseDto.getRates().get(0).getMid();
        }
    }
}
