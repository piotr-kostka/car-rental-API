package com.kodilla.rental.mapper;

import com.kodilla.rental.domain.Car;
import com.kodilla.rental.domain.dto.CarDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarMapper {

    public Car mapToCar(final CarDto carDto) {
        return new Car(
                carDto.getCarId(),
                carDto.getModel(),
                carDto.getLicenseNumber(),
                carDto.getCarStatus(),
                carDto.getRentals()
        );
    }

    public CarDto mapToCarDto(final Car car) {
        return new CarDto(
                car.getCarId(),
                car.getModel(),
                car.getLicenseNumber(),
                car.getCarStatus(),
                car.getRentals()
        );
    }

    public List<CarDto> mapToCarDtoList(final List<Car> carList) {
        return carList.stream()
                .map(this::mapToCarDto)
                .collect(Collectors.toList());
    }
}
