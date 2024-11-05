package com.imt.td.architecture.common.model.mapper;

import com.imt.td.architecture.interfaces.rest.common.model.input.AbstractInput;
import com.imt.td.architecture.interfaces.rest.common.model.input.AbstractUpdateInput;
import com.imt.td.architecture.interfaces.rest.common.model.output.AbstractOutput;

public abstract class AbstractMapper<T, I, O> {
    public abstract T from(final I input);
    public abstract O to(final T object);
}
