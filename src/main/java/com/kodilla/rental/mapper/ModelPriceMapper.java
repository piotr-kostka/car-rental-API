package com.kodilla.rental.mapper;

import com.kodilla.rental.domain.ModelPrice;
import com.kodilla.rental.domain.dto.ModelPriceDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelPriceMapper {

    public ModelPrice mapToModelPrice(final ModelPriceDto modelPriceDto) {
        return new ModelPrice(
                modelPriceDto.getPriceId(),
                modelPriceDto.getPrice()
        );
    }

    public ModelPriceDto mapToModelPriceDto(final ModelPrice modelPrice) {
        return new ModelPriceDto(
                modelPrice.getPriceId(),
                modelPrice.getPrice()
        );
    }

    public List<ModelPriceDto> mapToModelPriceDtoList(final List<ModelPrice> modelPriceList) {
        return modelPriceList.stream()
                .map(this::mapToModelPriceDto)
                .collect(Collectors.toList());
    }
}
