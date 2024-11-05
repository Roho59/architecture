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
public class NotFoundException extends AbstractRestException {
    private static final long serialVersionUID = 1154853632131287699L;
    
    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    private final String message;
}
