package org.bank.accountmanagementservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.bank.accountmanagementservice.models.User;
import org.bank.accountmanagementservice.repositories.UserRepository;
import org.bank.accountmanagementservice.services.UserService;
import org.bank.accountmanagementservice.utils.errors.ModelNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void save(User user){
        userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(ModelNotFoundException::new);
    }

    public void update(User user){
        if(userRepository.findById(user.getId()).isPresent()) {
            userRepository.save(user);
        }
    }
}
