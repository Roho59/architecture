package com.imt.td.architecture.interfaces.rest.common.model.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstractInput implements Serializable {
    private static final long serialVersionUID = -389896001108556557L;
}
