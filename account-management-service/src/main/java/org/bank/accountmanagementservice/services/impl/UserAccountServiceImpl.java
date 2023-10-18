package org.bank.accountmanagementservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.bank.accountmanagementservice.dto.ReplenishBalanceDto;
import org.bank.accountmanagementservice.dto.UserAccountDto;
import org.bank.accountmanagementservice.models.UserAccount;
import org.bank.accountmanagementservice.repositories.UserAccountRepository;
import org.bank.accountmanagementservice.services.UserAccountService;
import org.bank.accountmanagementservice.utils.errors.ModelNotFoundException;
import org.bank.accountmanagementservice.utils.mappers.UserAccountMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    private final UserAccountMapper userAccountMapper;

    @Transactional(readOnly = true)
    public List<UserAccountDto> findAllByUserUniqueId(String uniqueNumber){
        return userAccountMapper.toDto(userAccountRepository.findAllByUserUniqueId(UUID.fromString(uniqueNumber)));
    }

    public void save(UserAccountDto dto){
        userAccountRepository.save(userAccountMapper.toEntity(dto));
    }

    public void topUpBalance(ReplenishBalanceDto dto) {
        UserAccount userAccount = userAccountRepository.findByNumber(dto.getAccountNumber()).orElseThrow(ModelNotFoundException::new);
        if(userAccount.getActive()) {
            userAccount.setBalance(userAccount.getBalance().add(dto.getAmount()));
        }
    }

    public void closeAccount(String accountNumber) {
       UserAccount userAccount = userAccountRepository.findByNumber(accountNumber).orElseThrow(ModelNotFoundException::new);
       userAccount.setActive(false);
    }

    @Transactional(readOnly = true)
    public UserAccountDto findById(Long id){
        return userAccountMapper.toDto(userAccountRepository.findById(id).orElseThrow(ModelNotFoundException::new));
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public boolean doTransactionIfAllowed(MultiValueMap<String, String> dataToCheck){
        UserAccount senderAccount = userAccountRepository.findByNumber(dataToCheck.get("accountNumbers").get(0)).orElseThrow(ModelNotFoundException::new);
        UserAccount receiverAccount = userAccountRepository.findByNumber(dataToCheck.get("accountNumbers").get(1)).orElseThrow(ModelNotFoundException::new);
        BigDecimal transactionAmount = new BigDecimal(dataToCheck.get("amount").get(0));


        if(senderAccount.getBalance().compareTo(transactionAmount) >= 0 && senderAccount.getActive() && receiverAccount.getActive()){
            senderAccount.setBalance(senderAccount.getBalance().subtract(transactionAmount));
            receiverAccount.setBalance(receiverAccount.getBalance().add(transactionAmount));
            return true;
        }
        else{
            return false;
        }
    }
}
