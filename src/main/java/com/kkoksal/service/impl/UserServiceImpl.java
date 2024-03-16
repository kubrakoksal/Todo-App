package com.kkoksal.service.impl;

import com.kkoksal.config.security.AuthJwtManager;
import com.kkoksal.dto.request.UserLoginRequest;
import com.kkoksal.dto.request.UserRegisterRequest;
import com.kkoksal.dto.response.UserRegisterResponse;
import com.kkoksal.exception.UserAlreadyExistsException;
import com.kkoksal.mapper.UserMapper;
import com.kkoksal.model.User;
import com.kkoksal.repository.UserRepository;
import com.kkoksal.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    AuthenticationManager authenticationManager;

    AuthJwtManager authJwtManager;

    @Override
    public UserRegisterResponse register(UserRegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistsException("A user is already registered with this e-mail address");
        }
        if (userRepository.existsByUserName(registerRequest.getUserName())) {
            throw new UserAlreadyExistsException("A user is already registered with this username");
        }
        User user = userMapper.convertRegisterRequestToUser(registerRequest);
        userRepository.save(user);
        return userMapper.convertUserToRegisterResponse(user);
    }

    @Override
    public String login(UserLoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authJwtManager.generateJwtToken(authentication);
    }
}
