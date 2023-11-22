package org.bank.accountmanagementservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.bank.accountmanagementservice.dto.ReplenishBalanceDto;
import org.bank.accountmanagementservice.dto.UserAccountDto;
import org.bank.accountmanagementservice.models.User;
import org.bank.accountmanagementservice.models.UserAccount;
import org.bank.accountmanagementservice.repositories.UserAccountRepository;
import org.bank.accountmanagementservice.repositories.UserRepository;
import org.bank.accountmanagementservice.services.UserAccountService;
import org.bank.accountmanagementservice.utils.enums.AccountType;
import org.bank.accountmanagementservice.utils.errors.ModelNotFoundException;
import org.bank.accountmanagementservice.utils.mappers.UserAccountMapper;
import org.bankApp.kafka.KafkaMessage;
import org.bankApp.util.MessageType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    private final UserAccountMapper userAccountMapper;

    private final UserRepository userRepository;

    private final KafkaTemplate<Long, Object> kafkaTemplate;


    @Transactional(readOnly = true)
    public List<UserAccountDto> findAllByUserUniqueId(String uniqueNumber){
        return userAccountMapper.toDto(userAccountRepository.findAllByUserUniqueId(UUID.fromString(uniqueNumber)));
    }

    public void save(UserAccountDto dto){
        User user = userRepository.findByUniqueUserId(dto.getUserId()).orElseThrow(ModelNotFoundException::new);
        UserAccount userAccount = userAccountMapper.toEntity(dto);
        userAccount.setUser(user);
        user.getUserAccounts().add(userAccount);
    }

    public void topUpBalance(ReplenishBalanceDto dto) {
        UserAccount userAccount = userAccountRepository.findByNumber(dto.getAccountNumber()).orElseThrow(ModelNotFoundException::new);
        if(userAccount.getActive()) {
            userAccount.setBalance(userAccount.getBalance().add(dto.getAmount()));
        }
    }

    public void closeAccount(UUID accountNumber) {
       UserAccount userAccount = userAccountRepository.findByNumber(accountNumber).orElseThrow(ModelNotFoundException::new);
       userAccount.setActive(false);
    }

    @Transactional(readOnly = true)
    public UserAccountDto findById(Long id){
        return userAccountMapper.toDto(userAccountRepository.findById(id).orElseThrow(ModelNotFoundException::new));
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public boolean doTransactionIfAllowed(MultiValueMap<String, String> dataToCheck){
        UserAccount senderAccount = userAccountRepository.findByNumber(UUID.fromString(dataToCheck.get("accountNumbers").get(0))).orElseThrow(ModelNotFoundException::new);
        UserAccount receiverAccount = userAccountRepository.findByNumber(UUID.fromString(dataToCheck.get("accountNumbers").get(1))).orElseThrow(ModelNotFoundException::new);
        BigDecimal transactionAmount = new BigDecimal(dataToCheck.get("amount").get(0));


        if(isAllowed(senderAccount, receiverAccount, transactionAmount)){
            senderAccount.setBalance(senderAccount.getBalance().subtract(transactionAmount));
            receiverAccount.setBalance(receiverAccount.getBalance().add(transactionAmount));
            kafkaTemplate.send("notification-topic", new KafkaMessage(LocalDateTime.now(), String.format("SUCCESSFUL TRANSACTION ON %s", transactionAmount), receiverAccount.getUser().getUniqueUserId(), MessageType.TRANSACTION));
            return true;
        }
        else{
            kafkaTemplate.send("notification-topic", new KafkaMessage(LocalDateTime.now(), String.format("TRANSACTION FAILED ON %s", transactionAmount), senderAccount.getUser().getUniqueUserId(), MessageType.ERROR));
            return false;
        }
    }

    private boolean isAllowed(UserAccount sender, UserAccount receiver, BigDecimal amount){
        if(receiver.getActive().equals(Boolean.FALSE)) return false;
        if(amount.compareTo(BigDecimal.ZERO) <= 0) return false;
        if(sender.getType() == AccountType.DEPOSIT && sender.getActive().equals(Boolean.TRUE)) return false;
        if(sender.getActive().equals(Boolean.FALSE)) return false;
        if(sender.getType() == AccountType.INVESTMENT && receiver.getType() != AccountType.CHECKING) return false;
        if(sender.getBalance().compareTo(amount) < 0) return false;
        return true;
    }
}
