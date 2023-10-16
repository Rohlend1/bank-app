package org.bank.accountmanagementservice.services;

import org.bank.accountmanagementservice.models.User;
import org.bank.accountmanagementservice.models.UserAccount;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface UserAccountService {

    List<UserAccount> findAll();

    void save(UserAccount userAccount);

    void delete(Long id);

    UserAccount findById(Long id);

    void update(UserAccount userAccount);

    boolean isTransactionAllowed(MultiValueMap<String, String> dataToCheck);
}
