package com.kkoksal.controller;

import com.kkoksal.dto.request.UserLoginRequest;
import com.kkoksal.dto.request.UserLogoutRequest;
import com.kkoksal.dto.request.UserRegisterRequest;
import com.kkoksal.dto.response.UserRegisterResponse;
import com.kkoksal.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody @Valid UserRegisterRequest registerRequest) {
        UserRegisterResponse user = userService.createUser(registerRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public String login(UserLoginRequest loginRequest) {
        return "Success Login";
    }

}
