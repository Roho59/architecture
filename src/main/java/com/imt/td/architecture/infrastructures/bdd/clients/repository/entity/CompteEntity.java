package com.imt.td.architecture.infrastructures.bdd.clients.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompteEntity {
    
    private String identifier;
    private String name;
    private String type;
    private Double solde;
}
