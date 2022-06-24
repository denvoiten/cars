package ru.cars.service;

import org.springframework.stereotype.Service;
import ru.cars.model.User;
import ru.cars.persistence.UserDBStore;

import java.util.Optional;

@Service
public class UserService {

    private final UserDBStore userDBStore;

    public UserService(UserDBStore userDBStore) {
        this.userDBStore = userDBStore;
    }

    public Optional<User> add(User user) {
        return userDBStore.add(user);
    }

    public Optional<User> findUserByEmailAndPwd(String phone, String password) {
        return userDBStore.findUserByPhoneAndPwd(phone, password);
    }
}
