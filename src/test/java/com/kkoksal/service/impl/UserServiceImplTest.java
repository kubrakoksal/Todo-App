package com.kkoksal.service.impl;

import com.kkoksal.config.security.AuthJwtManager;
import com.kkoksal.config.security.UserPrincipal;
import com.kkoksal.dto.request.UserLoginRequest;
import com.kkoksal.dto.request.UserRegisterRequest;
import com.kkoksal.dto.response.LoginResponse;
import com.kkoksal.dto.response.RegisterResponse;
import com.kkoksal.exception.UserAlreadyExistsException;
import com.kkoksal.mapper.UserMapper;
import com.kkoksal.model.User;
import com.kkoksal.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    public UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    AuthJwtManager authJwtManager;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void registerFailCase001() {
        when(userRepository.existsByEmail(any())).thenReturn(true);
        userService.register(new UserRegisterRequest());
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void registerFailCase002() {
        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.existsByUserName(any())).thenReturn(true);
        userService.register(new UserRegisterRequest());
    }

    @Test()
    public void registerSuccessCase003() {
        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.existsByUserName(any())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(new User());
        when(userMapper.convertRegisterRequestToUser(any())).thenReturn(new User());
        when(userMapper.convertUserToRegisterResponse(any())).thenReturn(new RegisterResponse());

        userService.register(new UserRegisterRequest());

        verify(userRepository, times(1)).existsByEmail(any());
        verify(userRepository, times(1)).existsByUserName(any());
        verify(userRepository, times(1)).save(any());
        verify(userRepository, times(1)).save(any());
        verify(userMapper, times(1)).convertRegisterRequestToUser(any());
        verify(userMapper, times(1)).convertUserToRegisterResponse(any());

    }

    @Test()
    public void loginSuccessCase001() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3Mi";
        Authentication authentication = mock(Authentication.class);
        String userName = "username";
        UserPrincipal userPrincipal = UserPrincipal.builder().
                userName(userName).
                build();

        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(authJwtManager.generateJwtToken(any())).thenReturn(token);
        when(authentication.getPrincipal()).thenReturn(userPrincipal);

        LoginResponse loginResponse = userService.login(new UserLoginRequest());

        verify(authenticationManager, times(1)).authenticate(any());
        verify(authJwtManager, times(1)).generateJwtToken(any());

        assertEquals(loginResponse.getToken(), token);
        assertEquals(loginResponse.getUserName(), userName);
    }
}