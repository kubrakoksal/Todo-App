package com.kkoksal.mapper;

import com.kkoksal.dto.request.UserRegisterRequest;
import com.kkoksal.dto.response.UserRegisterResponse;
import com.kkoksal.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    public abstract User convertRegisterRequestToUser(UserRegisterRequest registerRequest);

    public abstract UserRegisterResponse convertUserToRegisterResponse(User user);

    @Named(value = "encodePassword")
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }


}
