package com.kodilla.rental.mapper;

import com.kodilla.rental.domain.Manufacturer;
import com.kodilla.rental.domain.dto.ManufacturerDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManufacturerMapper {

    public Manufacturer mapToManufacturer(final ManufacturerDto manufacturerDto) {
        return new Manufacturer(
                manufacturerDto.getManufacturerId(),
                manufacturerDto.getName(),
                manufacturerDto.getModels()
        );
    }

    public ManufacturerDto mapToManufacturerDto(final Manufacturer manufacturer) {
        return new ManufacturerDto(
                manufacturer.getManufacturerId(),
                manufacturer.getName(),
                manufacturer.getModels()
        );
    }

    public List<ManufacturerDto> mapToManufacturerDtoList(final List<Manufacturer> manufacturerList) {
        return manufacturerList.stream()
                .map(this::mapToManufacturerDto)
                .collect(Collectors.toList());
    }
}
