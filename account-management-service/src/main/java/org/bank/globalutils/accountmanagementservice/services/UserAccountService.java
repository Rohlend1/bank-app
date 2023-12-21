package org.bank.globalutils.accountmanagementservice.services;

import org.bank.globalutils.accountmanagementservice.dto.ReplenishBalanceDto;
import org.bank.globalutils.accountmanagementservice.dto.UserAccountDto;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.UUID;

public interface UserAccountService {

    List<UserAccountDto> findAllByUserUniqueId(String uniqueNumber);

    void save(UserAccountDto userAccount);
    void topUpBalance(ReplenishBalanceDto dto);
    void closeAccount(UUID accountNumber);

    UserAccountDto findById(Long id);

    boolean doTransactionIfAllowed(MultiValueMap<String, String> dataToCheck);
}
