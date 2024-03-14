package com.kkoksal.service;

import com.kkoksal.dto.request.UserRegisterRequest;
import com.kkoksal.dto.response.UserRegisterResponse;

public interface UserService {

    public UserRegisterResponse createUser(UserRegisterRequest registerRequest);

}
