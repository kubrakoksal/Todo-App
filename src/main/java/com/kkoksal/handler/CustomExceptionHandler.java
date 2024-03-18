package com.kkoksal.handler;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.kkoksal.dto.response.ErrorResponseModel;
import com.kkoksal.exception.CustomTokenExpiredException;
import com.kkoksal.exception.TodoItemNotFoundException;
import com.kkoksal.exception.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({
            BadCredentialsException.class,
            UsernameNotFoundException.class,
            JWTCreationException.class,
            JWTVerificationException.class
    })
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponseModel> handleBadCredentialsException() {
        ErrorResponseModel badCred = ErrorResponseModel.builder().
                message("Username or password is incorrect").
                status(BAD_REQUEST.value()).build();
        return ResponseEntity.badRequest().body(badCred);
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponseModel> handleArgumentValidationException(MethodArgumentNotValidException e) {
        StringBuilder errorMessage = new StringBuilder();
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errorMessage.append(fieldError.getDefaultMessage()).append(", ");
        }
        ErrorResponseModel validationErrors = ErrorResponseModel.builder().
                message(errorMessage.toString()).
                status(BAD_REQUEST.value()).build();
        return ResponseEntity.badRequest().body(validationErrors);
    }

    @ExceptionHandler({
            UserAlreadyExistsException.class,
            CustomTokenExpiredException.class,
            TodoItemNotFoundException.class
    })
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponseModel> handleCustomExceptions(Exception e) {
        ErrorResponseModel customErr = ErrorResponseModel.builder().
                message(e.getMessage()).
                status(BAD_REQUEST.value()).build();
        return ResponseEntity.badRequest().body(customErr);
    }

    @ExceptionHandler({
            Exception.class
    })
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseModel> handleInternalException(Exception e) {
        ErrorResponseModel internalErr = ErrorResponseModel.builder().
                message(e.getMessage()).
                status(INTERNAL_SERVER_ERROR.value()).build();
        return ResponseEntity.internalServerError().body(internalErr);
    }

}
