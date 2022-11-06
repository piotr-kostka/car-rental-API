package com.kodilla.rental.client;

import com.kodilla.rental.config.NbpConfig;
import com.kodilla.rental.domain.dto.api.nbp.NbpResponseDto;
import com.kodilla.rental.domain.dto.api.nbp.RatesDto;
import com.kodilla.rental.domain.enums.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NbpApiClientTestSuite {

    @InjectMocks
    private NbpApiClient nbpApiClient;

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private NbpConfig nbpConfig;

    @Test
    public void nbpPLNTest() {
        //Given
        //When
        double expected = nbpApiClient.getExchangeRate(Currency.PLN);
        //Then
        assertEquals(1, expected);
    }

    @Test
    public void nbpOtherCurrencyTest() throws URISyntaxException {
        //Given
        when(nbpConfig.getNbpApiEndpoint()).thenReturn("https://test.com/api/exchangerates/rates/a/");
        List<RatesDto> ratesDtoList = new ArrayList<>();
        ratesDtoList.add(new RatesDto(4.66));
        NbpResponseDto responseDto =new NbpResponseDto(ratesDtoList);

        URI uri = new URI("https://test.com/api/exchangerates/rates/a/EUR");
        when(restTemplate.getForObject(uri, NbpResponseDto.class)).thenReturn(responseDto);

        //When
        double expected = nbpApiClient.getExchangeRate(Currency.EUR);

        //Then
        assertEquals(4.66, expected);
    }
}
