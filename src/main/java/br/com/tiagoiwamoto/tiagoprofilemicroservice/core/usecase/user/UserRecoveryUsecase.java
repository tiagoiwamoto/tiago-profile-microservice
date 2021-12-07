package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.user;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 07/10/2021 | 20:17
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.dataprovider.repository.UserRepository;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.User;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.error.UserNotfoundException;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.assembler.UserAssembler;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.dto.ApiResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.dto.UserDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.util.AppMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserRecoveryUsecase {

    private final UserRepository userRepository;
    private final UserAssembler userAssembler;

    public ResponseDto prepareToRecoverUsers(Pageable pageable){
        Page<User> userPage = this.userRepository.findAll(pageable);
        log.info("users found on data base -> {}", userPage.getTotalElements());

        // Convert to a List<UserDto>
        List<UserDto> listUsersDto = userPage.map(UserDto::buildUserDtoFromUser).getContent();
        // Convert to a EntityModel<UserDto> to add links to each User
        CollectionModel<EntityModel<UserDto>> userDtoCollection = this.userAssembler.toCollectionModel(listUsersDto);
        // Convert to a pageable interface to return correct number of elements and pages
        PageImpl<EntityModel<UserDto>> userDtoPage = new PageImpl<>(
                new ArrayList<>(userDtoCollection.getContent()),
                pageable,
                userPage.getTotalElements()
        );
        userDtoPage.getTotalElements();
        log.info("users converted to userDto -> {}", userDtoPage.getTotalElements());
        return ApiResponseDto.of(
                HttpStatus.OK.name(),
                userDtoPage,
                AppMessage.API_SUCCESS);
    }

    public ResponseDto prepareToRecoverUser(String id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        Map<String, Object> userPreference = new LinkedHashMap<>();
        if(optionalUser.isEmpty()){
            log.error("user with id {} not found on data base", id);
            throw new UserNotfoundException();
        }
        UserDto userDto = UserDto.buildUserDtoFromUser(optionalUser.get());
        userDto.setPreference(userPreference);
        userDto = this.userAssembler.toModel(userDto).getContent();
        log.info("user found and converted to userDto -> {}", userDto);
        return ApiResponseDto.of(HttpStatus.OK.name(), this.userAssembler.toModel(userDto), AppMessage.API_SUCCESS);
    }

    public UserDto prepareTologin(String username) {
        Optional<User> optionalUser = this.userRepository.findByUsername(username);
        Map<String, Object> userPreference = new LinkedHashMap<>();
        if(optionalUser.isEmpty()){
            log.error("user with username {} not found on data base", username);
            throw new UserNotfoundException();
        }
        UserDto userDto = UserDto.buildUserDtoFromUser(optionalUser.get());
        userDto.setPreference(userPreference);
        userDto = this.userAssembler.toModel(userDto).getContent();
        log.info("user found and converted to userDto -> {}", userDto);
        return userDto;
    }

}
