package com.kodilla.rental.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.rental.domain.Car;
import com.kodilla.rental.domain.Manufacturer;
import com.kodilla.rental.domain.enums.model.BodyType;
import com.kodilla.rental.domain.enums.model.FuelType;
import com.kodilla.rental.domain.enums.model.TransmissionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelDto {

    private long modelId;
    private Manufacturer manufacturer;
    private String name;
    private double engineSize;
    private BodyType bodyType;
    private int productionYear;
    private String color;
    private int seatsQuantity;
    private int doorQuantity;
    private FuelType fuelType;
    private TransmissionType transmissionType;

    @JsonIgnore
    private Set<Car> cars;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModelDto modelDto = (ModelDto) o;

        if (Double.compare(modelDto.engineSize, engineSize) != 0) return false;
        if (productionYear != modelDto.productionYear) return false;
        if (seatsQuantity != modelDto.seatsQuantity) return false;
        if (doorQuantity != modelDto.doorQuantity) return false;
        if (!manufacturer.equals(modelDto.manufacturer)) return false;
        if (!name.equals(modelDto.name)) return false;
        if (bodyType != modelDto.bodyType) return false;
        if (!color.equals(modelDto.color)) return false;
        if (fuelType != modelDto.fuelType) return false;
        return transmissionType == modelDto.transmissionType;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = 31 * name.hashCode();
        temp = Double.doubleToLongBits(engineSize);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + bodyType.hashCode();
        result = 31 * result + productionYear;
        result = 31 * result + color.hashCode();
        result = 31 * result + seatsQuantity;
        result = 31 * result + doorQuantity;
        result = 31 * result + fuelType.hashCode();
        result = 31 * result + transmissionType.hashCode();
        return result;
    }
}
