package org.bank.accountmanagementservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.bank.accountmanagementservice.models.UserAccount;
import org.bank.accountmanagementservice.repositories.UserAccountRepository;
import org.bank.accountmanagementservice.services.UserAccountService;
import org.bank.accountmanagementservice.utils.errors.ModelNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public List<UserAccount> findAll(){
        return userAccountRepository.findAll();
    }

    public void save(UserAccount userAccount){
        userAccountRepository.save(userAccount);
    }

    public void delete(Long id){
        userAccountRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public UserAccount findById(Long id){
        return userAccountRepository.findById(id).orElseThrow(ModelNotFoundException::new);
    }

    public void update(UserAccount userAccount){
        if(userAccountRepository.findById(userAccount.getId()).isPresent()) {
            userAccountRepository.save(userAccount);
        }
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public boolean isTransactionAllowed(MultiValueMap<String, String> dataToCheck){
        UserAccount senderAccount = userAccountRepository.findByNumber(dataToCheck.get("accountNumbers").get(0)).orElseThrow(ModelNotFoundException::new);
        UserAccount receiverAccount = userAccountRepository.findByNumber(dataToCheck.get("accountNumbers").get(1)).orElseThrow(ModelNotFoundException::new);
        BigDecimal transactionAmount = new BigDecimal(dataToCheck.get("amount").get(0));


        if(senderAccount.getBalance().compareTo(transactionAmount) >= 0){
            senderAccount.setBalance(senderAccount.getBalance().subtract(transactionAmount));
            receiverAccount.setBalance(receiverAccount.getBalance().add(transactionAmount));
            return true;
        }
        else{
            return false;
        }
    }
}
