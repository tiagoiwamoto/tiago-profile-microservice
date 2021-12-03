package br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.assembler;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 10/10/2021 | 07:21
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.dto.UserDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.resource.UserResource;
import lombok.SneakyThrows;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler implements SimpleRepresentationModelAssembler<UserDto> {

    @SneakyThrows
    @Override
    public void addLinks(EntityModel<UserDto> resource) {
        String id = Objects.requireNonNull(resource.getContent()).getId();
        Link selfLink = linkTo(methodOn(UserResource.class).recoverUser(id))
                .withSelfRel()
                .withType("GET");

        Link editLink = linkTo(methodOn(UserResource.class).update(new UserDto()))
                .withSelfRel()
                .withType("PUT");

        Link removeLink = linkTo(methodOn(UserResource.class).deleteUser(id))
                .withSelfRel()
                .withType("DELETE");

        resource.add(selfLink, editLink, removeLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<UserDto>> resources) {

    }
}
