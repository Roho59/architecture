package com.imt.td.architecture.interfaces.rest.common.model.mapper;

import com.imt.td.architecture.common.model.mapper.AbstractMapper;
import com.imt.td.architecture.interfaces.rest.common.model.input.AbstractInput;
import com.imt.td.architecture.interfaces.rest.common.model.input.AbstractUpdateInput;
import com.imt.td.architecture.interfaces.rest.common.model.output.AbstractOutput;

public abstract class AbstractControllerMapper<T, I extends AbstractInput, U extends AbstractUpdateInput, O extends AbstractOutput> extends AbstractMapper<T, I, O> {
    public abstract T from(final U input, final T alreadySaved);
}
