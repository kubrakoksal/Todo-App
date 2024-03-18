package com.kkoksal.service;

import com.kkoksal.dto.request.UserLoginRequest;
import com.kkoksal.dto.request.UserRegisterRequest;
import com.kkoksal.dto.response.LoginResponse;
import com.kkoksal.dto.response.RegisterResponse;

public interface UserService {

    public RegisterResponse register(UserRegisterRequest registerRequest);

    LoginResponse login(UserLoginRequest loginRequest);
}
