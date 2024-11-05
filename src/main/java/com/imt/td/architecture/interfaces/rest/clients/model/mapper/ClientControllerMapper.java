package com.imt.td.architecture.interfaces.rest.clients.model.mapper;

import com.imt.td.architecture.business.clients.model.Client;
import com.imt.td.architecture.common.model.GenreEnum;
import com.imt.td.architecture.interfaces.rest.clients.model.input.ClientInput;
import com.imt.td.architecture.interfaces.rest.clients.model.input.ClientUpdateInput;
import com.imt.td.architecture.interfaces.rest.clients.model.output.ClientOutput;
import com.imt.td.architecture.interfaces.rest.common.model.mapper.AbstractControllerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ClientControllerMapper extends AbstractControllerMapper<Client, ClientInput, ClientUpdateInput, ClientOutput> {
    @Override
    public Client from(final ClientInput input) {
        return Client.builder()
                     .lastname(input.getLastname())
                     .firstname(input.getFirstname())
                     .genre(GenreEnum.fromOrDefault(input.getGenre()))
                     .build();
    }
    
    @Override
    public Client from(final ClientUpdateInput input, final Client alreadySaved) {
        return alreadySaved.toBuilder()
                     .lastname(input.getLastname().defaultIfNotOverwrite(alreadySaved.getLastname()))
                     .firstname(input.getFirstname().defaultIfNotOverwrite(alreadySaved.getFirstname()))
                     .genre(input.getGenre().defaultIfNotOverwrite(GenreEnum::fromOrDefault, alreadySaved.getGenre()))
                     .build();
    }
    
    @Override
    public ClientOutput to(final Client client) {
        return ClientOutput.builder()
                           .identifier(client.getIdentifier().toString())
                           .lastname(client.getLastname())
                           .firstname(client.getFirstname())
                           .genre(client.getGenre().name())
                           .build();
    }
}
