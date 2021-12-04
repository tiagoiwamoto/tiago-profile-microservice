package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.user;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 07:24
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.dataprovider.gateway.dto.ViacepDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.dataprovider.repository.UserRepository;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.User;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.error.UserAlreadyExistsException;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.error.UserSaveException;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.viacep.ViacepUsecase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.dto.ApiResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.dto.UserDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.util.AppMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@AllArgsConstructor
@Slf4j
public class UserCreateUsecase {

    private final UserRepository userRepository;
    private final ViacepUsecase viacepUsecase;

    public ResponseDto prepareToCreateOrUpdateUser(UserDto userDto){
        if(this.userRepository.findByUsernameOrName(userDto.getUsername(), userDto.getName()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        String cep = userDto.getCep().trim().replaceAll("[-.,]", "");
        log.info("cep to found on Viacep -> {}", cep);
        ViacepDto viacepDto = this.viacepUsecase.connectToViacep(cep);
        log.info("Address recovery from viacep -> {}", viacepDto.toString());
        UserDto createdUserDto = UserDto.buildUserDtoFromUser(buildUserFromViacep(viacepDto, userDto));
        log.info("User is saved, and converted to DTO -> {}", createdUserDto);
        log.info("Operation of create a new user is completed with success");

        return ApiResponseDto.of(HttpStatus.CREATED.name(), createdUserDto, AppMessage.API_SUCCESS);
    }

    private User buildUserFromViacep(ViacepDto viacepDto, UserDto userDto){
        User userToCreate = User.build();
        userToCreate.setId(Objects.isNull(userDto.getId()) ? null : userDto.getId());
        userToCreate.setName(userDto.getName());
        userToCreate.setUsername(userDto.getUsername());
        userToCreate.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        userToCreate.setAddress(viacepDto.getLogradouro());
        userToCreate.setNeighborhood(viacepDto.getBairro());
        userToCreate.setCity(viacepDto.getLocalidade());
        userToCreate.setState(viacepDto.getUf());
        userToCreate.setCep(viacepDto.getCep());
        userToCreate.setCreatedAt(Objects.isNull(userDto.getCreatedAt()) ? LocalDateTime.now() : userDto.getCreatedAt());
        log.info("user to create or update on database -> {}", userToCreate);

        try{
            return this.userRepository.save(userToCreate);
        }catch (Exception e){
            throw new UserSaveException();
        }
    }
}
