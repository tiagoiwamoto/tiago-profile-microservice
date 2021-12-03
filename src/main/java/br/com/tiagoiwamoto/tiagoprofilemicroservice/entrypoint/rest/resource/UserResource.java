package br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.resource;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 07:27
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.user.UserCreateUsecase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.user.UserDeleteUsecase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.user.UserRecoveryUsecase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

@Api(tags = {"Gerenciamento de usuário"}, description = "Api utilizando arquitetura limpa (Clean architecture)")
@RestController
@RequestMapping(path = "/api/v1/users")
@AllArgsConstructor
@Slf4j
public class UserResource {

    private final UserCreateUsecase userCreateUsecase;
    private final UserRecoveryUsecase userRecoveryUsecase;
    private final UserDeleteUsecase userDeleteUsecase;

    @ApiOperation(value = "Grava um novo usuário em nossa base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody @Valid UserDto userDto){
        log.info("starting create() of new user... {}", userDto.toString());
        return new ResponseEntity<>(
                this.userCreateUsecase.prepareToCreateOrUpdateUser(userDto),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Grava um novo usuário em nossa base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro atualizado com sucesso ou registro não localizado em nossa base de dados"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody @Valid UserDto userDto){
        log.info("starting update() a existing user... {}", userDto.toString());
        return new ResponseEntity<>(
                this.userCreateUsecase.prepareToCreateOrUpdateUser(userDto),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Recupera uma lista de usuários paginados pelo número máximo de 100 registros.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registros recuperados com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @GetMapping
    public ResponseEntity<ResponseDto> recoverListOfUsers(Pageable pageable){
        log.info("starting recoverListOfUsers()... {}", pageable);
        return new ResponseEntity<>(
                this.userRecoveryUsecase.prepareToRecoverUsers(pageable),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Recupera um usuário pelo seu número identificador único")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro recuperado com sucesso ou registro não localizado em nossa base de dados"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @GetMapping(path = "/{userId}")
    public ResponseEntity<ResponseDto> recoverUser(@PathVariable(name = "userId") String userId) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        log.info("starting recoverUser() with id {}...", userId);
        return new ResponseEntity<>(this.userRecoveryUsecase.prepareToRecoverUser(userId), HttpStatus.OK);
    }

    @ApiOperation(value = "Realiza a exclusão lógica de um usuário pelo seu número identificador único")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exclusão lógica realizada com sucesso ou registro não localizado em nossa base de dados"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable(name = "userId") String userId){
        log.info("starting recoverUser() with id {}...", userId);
        return new ResponseEntity<>(this.userDeleteUsecase.prepareToDeleteUser(userId), HttpStatus.OK);
    }

}
