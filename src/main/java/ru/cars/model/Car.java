package ru.cars.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@RequiredArgsConstructor
@Setter
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "brand_id", foreignKey = @ForeignKey(name = "BRAND_ID_FK"))
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "body_id", foreignKey = @ForeignKey(name = "BODY_ID_FK"))
    private Body body;
    @ManyToOne
    @JoinColumn(name = "transmission_id", foreignKey = @ForeignKey(name = "TRANSMISSION_ID_FK"))
    private Transmission transmission;
    private String year;

    public static Car of(String name, Brand brand, Body body, Transmission transmission, String year) {
        var car = new Car();
        car.name = name;
        car.brand = brand;
        car.body = body;
        car.transmission = transmission;
        car.year = year;
        return car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
