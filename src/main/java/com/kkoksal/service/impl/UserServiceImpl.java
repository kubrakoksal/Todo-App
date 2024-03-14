package com.kkoksal.service.impl;

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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    @Override
    public UserRegisterResponse createUser(UserRegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistsException("A user is already registered with this e-mail address");
        }
        User user = userMapper.convertRegisterRequestToUser(registerRequest);
        userRepository.save(user);
        return userMapper.convertUserToRegisterResponse(user);
    }
}
