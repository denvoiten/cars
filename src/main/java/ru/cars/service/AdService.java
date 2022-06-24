package ru.cars.service;

import org.springframework.stereotype.Service;
import ru.cars.model.Advertisement;
import ru.cars.model.Body;
import ru.cars.model.Brand;
import ru.cars.model.Transmission;
import ru.cars.persistence.AdDBStore;

import java.util.List;

@Service
public class AdService {
    private final AdDBStore adDBStore;

    public AdService(AdDBStore adDBStore) {
        this.adDBStore = adDBStore;
    }

    public Advertisement add(Advertisement advertisement) {
        return adDBStore.add(advertisement);
    }

    public void delete(int id) {
        adDBStore.delete(id);
    }

    public void update(Advertisement advertisement) {
        adDBStore.update(advertisement);
    }

    public List<Advertisement> findAll() {
        return adDBStore.findAll();
    }

    public Advertisement findByID(int id) {
        return adDBStore.findByID(id);
    }

    public void setSoldById(int id) {
        adDBStore.setSoldById(id);
    }

    public List<Brand> findAllBrands() {
        return adDBStore.findAllBrands();
    }

    public List<Body> findAllBody() {
        return adDBStore.findAllBody();
    }

    public List<Transmission> findAllTransmission() {
        return adDBStore.findAllTransmission();
    }

    public Brand findBrandById(int id) {
        return adDBStore.findBrandById(id);
    }

    public Body findBodyById(int id) {
        return adDBStore.findBodyById(id);
    }

    public Transmission findTransmissionById(int id) {
        return adDBStore.findTransmissionById(id);
    }
}
