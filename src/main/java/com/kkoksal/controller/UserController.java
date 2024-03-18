package com.kkoksal.controller;

import com.kkoksal.dto.request.UserLoginRequest;
import com.kkoksal.dto.request.UserRegisterRequest;
import com.kkoksal.dto.response.LoginResponse;
import com.kkoksal.dto.response.RegisterResponse;
import com.kkoksal.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Tag(name = "User", description = "User Authentication Operations Service")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register User")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid UserRegisterRequest registerRequest) {
        RegisterResponse user = userService.register(registerRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    @Operation(summary = "Login User With UserName or Email")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid UserLoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

}
