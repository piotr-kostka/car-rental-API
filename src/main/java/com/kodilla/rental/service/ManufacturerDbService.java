package com.kodilla.rental.service;

import com.kodilla.rental.domain.Manufacturer;
import com.kodilla.rental.domain.dto.ManufacturerDto;
import com.kodilla.rental.exception.notFound.ManufacturerNotFoundException;
import com.kodilla.rental.mapper.ManufacturerMapper;
import com.kodilla.rental.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturerDbService {

    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerMapper manufacturerMapper;

    public List<ManufacturerDto> getAllManufacturers() {
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        return manufacturerMapper.mapToManufacturerDtoList(manufacturers);
    }

    public ManufacturerDto getManufacturer(final long manufacturerId) throws ManufacturerNotFoundException {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        return manufacturerMapper.mapToManufacturerDto(manufacturer);
    }

    @Transactional
    public ManufacturerDto createManufacturer(final ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = manufacturerMapper.mapToManufacturer(manufacturerDto);
        Manufacturer savedManufacturer = manufacturerRepository.save(manufacturer);
        return manufacturerMapper.mapToManufacturerDto(savedManufacturer);
    }

    @Transactional
    public ManufacturerDto updateManufacturer(final ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = manufacturerMapper.mapToManufacturer(manufacturerDto);
        Manufacturer savedManufacturer = manufacturerRepository.save(manufacturer);
        return manufacturerMapper.mapToManufacturerDto(savedManufacturer);
    }

    @Transactional
    public void deleteManufacturer(final long manufacturerId) {
        manufacturerRepository.deleteById(manufacturerId);
    }
}
