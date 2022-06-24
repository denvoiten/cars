package ru.cars.persistence;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.cars.model.Advertisement;
import ru.cars.model.Body;
import ru.cars.model.Brand;
import ru.cars.model.Transmission;

import java.util.List;

@Repository
public class AdDBStore implements StoreTransaction {

    private final SessionFactory sf;

    public AdDBStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Advertisement add(Advertisement advertisement) {
        return (Advertisement) transaction(session -> session.merge(advertisement), sf);
    }

    public void delete(int id) {
        transaction(session -> session
                .createQuery("DELETE Advertisement a WHERE a.id = :aID")
                .setParameter("aID", id)
                .executeUpdate(), sf);
    }

    public void update(Advertisement advertisement) {
        transaction(session -> {
                    session.update(advertisement);
                    return advertisement;
                }, sf
        );
    }

    public List<Advertisement> findAll() {
        return transaction(session -> session
                        .createQuery("from Advertisement a ORDER BY a.id")
                        .getResultList(),
                sf);
    }

    public Advertisement findByID(int id) {
        return (Advertisement) transaction(session -> session
                        .createQuery("from Advertisement a WHERE a.id = :aID")
                        .setParameter("aID", id)
                        .uniqueResult(),
                sf);
    }

    public void setSoldById(int id) {
        transaction(session -> session
                        .createQuery("UPDATE Advertisement a SET a.sold = :flag "
                                + "WHERE a.id = :id")
                        .setParameter("flag", true)
                        .setParameter("id", id)
                        .executeUpdate(),
                sf);
    }

    public List<Brand> findAllBrands() {
        return transaction(session -> session.createQuery("FROM Brand").getResultList(), sf);
    }

    public List<Body> findAllBody() {
        return transaction(session -> session.createQuery("FROM Body").getResultList(), sf);
    }

    public List<Transmission> findAllTransmission() {
        return transaction(session -> session.createQuery("FROM Transmission ").getResultList(), sf);
    }

    public Brand findBrandById(int id) {
        return (Brand) transaction(session -> session.createQuery("FROM Brand WHERE id = :bID")
                .setParameter("bID", id)
                .uniqueResult(), sf);
    }

    public Body findBodyById(int id) {
        return (Body) transaction(session -> session.createQuery("FROM Body WHERE id = :bID")
                .setParameter("bID", id)
                .uniqueResult(), sf);
    }

    public Transmission findTransmissionById(int id) {
        return (Transmission) transaction(session -> session.createQuery("FROM Transmission WHERE id = :tID")
                .setParameter("tID", id)
                .uniqueResult(), sf);
    }
}
