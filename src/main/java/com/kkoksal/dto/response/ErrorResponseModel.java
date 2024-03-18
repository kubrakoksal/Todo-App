package com.kkoksal.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ErrorResponseModel implements Serializable {
    String message;
    Integer status;
}
