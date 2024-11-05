package com.imt.td.architecture.interfaces.rest.comptes.model.input;

import com.imt.td.architecture.interfaces.rest.common.model.input.AbstractInput;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class CompteInput extends AbstractInput {
    private static final long serialVersionUID = -298328949529281732L;
    private String name;
    private String type;
}
