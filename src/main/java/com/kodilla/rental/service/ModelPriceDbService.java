package com.kodilla.rental.service;

import com.kodilla.rental.domain.ModelPrice;
import com.kodilla.rental.domain.dto.ModelPriceDto;
import com.kodilla.rental.exception.notFound.ModelPriceNotFoundException;
import com.kodilla.rental.mapper.ModelPriceMapper;
import com.kodilla.rental.repository.ModelPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelPriceDbService {

    private final ModelPriceRepository modelPriceRepository;
    private final ModelPriceMapper modelPriceMapper;

    public List<ModelPriceDto> getModelPrices() {
        List<ModelPrice> modelPrices = modelPriceRepository.findAll();
        return modelPriceMapper.mapToModelPriceDtoList(modelPrices);
    }

    public ModelPriceDto getModelPrice(final long priceId) throws ModelPriceNotFoundException {
        ModelPrice modelPrice = modelPriceRepository.findById(priceId).orElseThrow(() -> new ModelPriceNotFoundException(priceId));
        return modelPriceMapper.mapToModelPriceDto(modelPrice);
    }

    @Transactional
    public ModelPriceDto createModelPrice(final ModelPriceDto priceDto) {
        ModelPrice modelPrice = modelPriceMapper.mapToModelPrice(priceDto);
        ModelPrice savedModelPrice = modelPriceRepository.save(modelPrice);
        return modelPriceMapper.mapToModelPriceDto(savedModelPrice);
    }

    @Transactional
    public ModelPriceDto updateModelPrice(final ModelPriceDto priceDto) {
        ModelPrice modelPrice = modelPriceMapper.mapToModelPrice(priceDto);
        ModelPrice savedModelPrice = modelPriceRepository.save(modelPrice);
        return modelPriceMapper.mapToModelPriceDto(savedModelPrice);
    }

    @Transactional
    public void deleteModelPrice(final long priceId) {
        modelPriceRepository.deleteById(priceId);
    }
}
