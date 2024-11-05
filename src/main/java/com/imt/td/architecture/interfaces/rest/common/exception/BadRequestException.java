package com.imt.td.architecture.interfaces.rest.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;


@Getter
@RequiredArgsConstructor
@ToString
public class BadRequestException extends AbstractRestException {
    private static final long serialVersionUID = 8352240067912989562L;
    
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private final String message;
}