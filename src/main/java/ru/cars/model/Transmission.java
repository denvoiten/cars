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
@Table(name = "transmission")
public class Transmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transmission transmission = (Transmission) o;
        return id == transmission.id && Objects.equals(name, transmission.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}