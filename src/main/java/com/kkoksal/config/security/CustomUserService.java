package com.kkoksal.config.security;

import com.kkoksal.model.User;
import com.kkoksal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userLoginInfo) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameOrEmail(userLoginInfo, userLoginInfo)
                .orElseThrow(() -> new UsernameNotFoundException(("User not found with username or email : " + userLoginInfo)));

        return UserPrincipal.builder().
                userName(user.getUserName()).
                password(user.getPassword()).
                id(user.getId()).
                email(user.getEmail()).
                build();
    }
}
