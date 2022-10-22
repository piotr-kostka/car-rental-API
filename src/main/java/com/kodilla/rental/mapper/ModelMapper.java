package com.kodilla.rental.mapper;

import com.kodilla.rental.domain.Model;
import com.kodilla.rental.domain.dto.ModelDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelMapper {

    public Model mapToModel(final ModelDto modelDto) {
        return new Model(
                modelDto.getModelId(),
                modelDto.getManufacturer(),
                modelDto.getName(),
                modelDto.getEngineSize(),
                modelDto.getBodyType(),
                modelDto.getProductionYear(),
                modelDto.getColor(),
                modelDto.getSeatsQuantity(),
                modelDto.getDoorQuantity(),
                modelDto.getModelPrice(),
                modelDto.getFuelType(),
                modelDto.getTransmissionType(),
                modelDto.getCars()
        );
    }

    public ModelDto mapToModelDto(final Model model) {
        return new ModelDto(
                model.getModelId(),
                model.getManufacturer(),
                model.getName(),
                model.getEngineSize(),
                model.getBodyType(),
                model.getProductionYear(),
                model.getColor(),
                model.getSeatsQuantity(),
                model.getDoorQuantity(),
                model.getModelPrice(),
                model.getFuelType(),
                model.getTransmissionType(),
                model.getCars()
        );
    }

    public List<ModelDto> mapToModelDtoList(final List<Model> modelList) {
        return modelList.stream()
                .map(this::mapToModelDto)
                .collect(Collectors.toList());
    }
}
