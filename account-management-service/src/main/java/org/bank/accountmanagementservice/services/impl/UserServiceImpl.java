package org.bank.accountmanagementservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bank.accountmanagementservice.dto.UserDto;
import org.bank.accountmanagementservice.dto.UserRequestDto;
import org.bank.accountmanagementservice.dto.UserUpdateDto;
import org.bank.accountmanagementservice.models.KeycloakUser;
import org.bank.accountmanagementservice.models.User;
import org.bank.accountmanagementservice.repositories.UserRepository;
import org.bank.accountmanagementservice.services.UserService;
import org.bank.accountmanagementservice.utils.Credential;
import org.bank.accountmanagementservice.utils.errors.ModelNotFoundException;
import org.bank.accountmanagementservice.utils.mappers.KeycloakUserMapper;
import org.bank.accountmanagementservice.utils.mappers.UserMapper;
import org.bankApp.response.ResponseMessage;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {


    @Value("${keycloak.register-user.url}")
    private String registerUrl;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KeycloakUserMapper keycloakUserMapper;
    private final WebClient webClient;
    private final Keycloak keycloak;

    public UserDto findByUniqueId(String id){
        return userMapper.toDto(userRepository.findByUniqueUserId(UUID.fromString(id)).orElseThrow(ModelNotFoundException::new));
    }

    public ResponseMessage save(UserRequestDto requestDto){
        User user = userMapper.toEntity(userMapper.toUserDto(requestDto));
        user.setUniqueUserId(UUID.randomUUID());
        KeycloakUser keycloakUser = keycloakUserMapper.toKeycloakUser(requestDto);
        keycloakUser.setUniqueUserId(user.getUniqueUserId().toString());
        keycloakUser.setUsername(user.getFirstName()+" "+user.getLastName());
        keycloakUser.setEnabled(true);
        keycloakUser.setCredentials(new ArrayList<>(List.of(new Credential("password",requestDto.getPassword(),false))));
        keycloakUser.setEmailVerified(true);
        keycloakUser.setRealmRoles(new ArrayList<>(List.of("ROLE_USER")));
        log.info(keycloakUser.toString());
        try{
            webClient.post()
                    .uri(registerUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + keycloak.tokenManager().getAccessTokenString())
                    .body(BodyInserters.fromValue(keycloakUser))
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
            userRepository.save(user);
            return new ResponseMessage(HttpStatus.OK, HttpStatus.OK.value(), "SUCCESSFUL REGISTRATION", LocalDateTime.now());
        }
        catch (Exception e){
            log.info(e.getMessage());
            e.printStackTrace();
            return new ResponseMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "Wrong credentials", LocalDateTime.now());
        }
    }


    public void update(UserUpdateDto dto){
        userRepository.save(userMapper.toEntity(dto));
    }
}
