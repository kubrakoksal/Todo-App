package com.kkoksal.service;

import com.kkoksal.dto.request.UserLoginRequest;
import com.kkoksal.dto.request.UserRegisterRequest;
import com.kkoksal.dto.response.UserRegisterResponse;

public interface UserService {

    public UserRegisterResponse register(UserRegisterRequest registerRequest);

    String login(UserLoginRequest loginRequest);
}
