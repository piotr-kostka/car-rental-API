package com.kodilla.rental.service;

import com.kodilla.rental.domain.Manufacturer;
import com.kodilla.rental.domain.dto.ManufacturerDto;
import com.kodilla.rental.exception.alreadyExists.ManufacturerAlreadyExistException;
import com.kodilla.rental.exception.notFound.ManufacturerNotFoundException;
import com.kodilla.rental.mapper.ManufacturerMapper;
import com.kodilla.rental.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ManufacturerDto createManufacturer(final ManufacturerDto manufacturerDto) throws ManufacturerAlreadyExistException {

        List<String> allManufacturersNames = getAllManufacturers().stream()
                .map(ManufacturerDto::getName)
                .collect(Collectors.toList());

        if (!allManufacturersNames.contains(manufacturerDto.getName())) {
            Manufacturer manufacturer = manufacturerMapper.mapToManufacturer(manufacturerDto);
            Manufacturer savedManufacturer = manufacturerRepository.save(manufacturer);
            return manufacturerMapper.mapToManufacturerDto(savedManufacturer);
        } else {
            throw new ManufacturerAlreadyExistException();
        }
    }

    @Transactional
    public ManufacturerDto updateManufacturer(final ManufacturerDto manufacturerDto) throws ManufacturerAlreadyExistException, ManufacturerNotFoundException{

        List<String> allManufacturersNames = getAllManufacturers().stream()
                .map(ManufacturerDto::getName)
                .collect(Collectors.toList());

        if (!manufacturerRepository.existsById(manufacturerDto.getManufacturerId())) {
            throw new ManufacturerNotFoundException(manufacturerDto.getManufacturerId());
        } else if (allManufacturersNames.contains(manufacturerDto.getName())) {
            throw new ManufacturerAlreadyExistException();
        } else {
            Manufacturer manufacturer = manufacturerMapper.mapToManufacturer(manufacturerDto);
            Manufacturer savedManufacturer = manufacturerRepository.save(manufacturer);
            return manufacturerMapper.mapToManufacturerDto(savedManufacturer);
        }
    }

    @Transactional
    public void deleteManufacturer(final long manufacturerId) throws ManufacturerNotFoundException {

        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(manufacturerId);

        if (manufacturer.isPresent()) {
            manufacturerRepository.deleteById(manufacturerId);
        } else {
            throw new ManufacturerNotFoundException(manufacturerId);
        }
    }
}
