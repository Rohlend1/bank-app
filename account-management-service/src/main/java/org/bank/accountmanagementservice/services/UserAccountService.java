package org.bank.accountmanagementservice.services;

import org.bank.accountmanagementservice.dto.ReplenishBalanceDto;
import org.bank.accountmanagementservice.dto.UserAccountDto;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface UserAccountService {

    List<UserAccountDto> findAllByUserUniqueId(String uniqueNumber);

    void save(UserAccountDto userAccount);
    void topUpBalance(ReplenishBalanceDto dto);
    void closeAccount(String accountNumber);

    UserAccountDto findById(Long id);

    boolean doTransactionIfAllowed(MultiValueMap<String, String> dataToCheck);
}
