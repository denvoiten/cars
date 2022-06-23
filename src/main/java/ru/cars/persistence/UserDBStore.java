package ru.cars.persistence;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.cars.model.User;

import java.util.Optional;

@Repository
public class UserDBStore implements StoreTransaction {
    private final SessionFactory sf;

    public UserDBStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Optional<User> add(User user) {
        return Optional.of((User) transaction(session -> session.merge(user), sf));
    }

    public Optional<User> findUserByEmailAndPwd(String email, String password) {
        return transaction(session -> session
                        .createQuery("FROM User u WHERE u.email = :email AND u.password = :password")
                        .setParameter("email", email)
                        .setParameter("password", password)
                        .uniqueResultOptional(),
                sf);
    }
}
