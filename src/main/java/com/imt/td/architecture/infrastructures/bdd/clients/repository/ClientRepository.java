package com.imt.td.architecture.infrastructures.bdd.clients.repository;

import com.imt.td.architecture.infrastructures.bdd.clients.repository.entity.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<ClientEntity, String> {
}
