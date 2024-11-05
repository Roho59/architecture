package com.imt.td.architecture.interfaces.rest.common.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractRestException extends RuntimeException {
    private static final long serialVersionUID = 2005704864736899722L;
    
    public abstract HttpStatus getHttpStatus();
    public String getType(){
        return this.getClass().getSimpleName();
    }
}
