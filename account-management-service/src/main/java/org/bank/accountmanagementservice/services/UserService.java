package org.bank.accountmanagementservice.services;

import org.bank.accountmanagementservice.models.User;
import org.bank.accountmanagementservice.utils.errors.ModelNotFoundException;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void save(User user);

    void delete(Long id);

    User findById(Long id);

    void update(User user);
}
