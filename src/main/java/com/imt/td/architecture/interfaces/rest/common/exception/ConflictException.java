package com.imt.td.architecture.interfaces.rest.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@Getter
@RequiredArgsConstructor
@ToString
public class ConflictException extends AbstractRestException {
    private static final long serialVersionUID = 3341536729233338474L;
    
    private final HttpStatus httpStatus = HttpStatus.CONFLICT;
    private final String message;
}
