package com.cashrich.backend.DAO;

import com.cashrich.backend.Entity.RequestResponseLogs;
import com.cashrich.backend.Entity.User;

import java.util.Optional;

public interface UserDao {

    User createUser(User user);

    Optional<User> findUserByEmail(String email);

    User updateUser (User user);

    RequestResponseLogs saveResponse(RequestResponseLogs requestResponseLogs);
}
