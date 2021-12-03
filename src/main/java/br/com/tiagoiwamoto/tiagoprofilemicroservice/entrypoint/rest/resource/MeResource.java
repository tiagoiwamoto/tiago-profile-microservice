package br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.resource;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 22/10/2021 | 06:58
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Me;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.me.MeCreateUpdateUseCase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.me.MeRecoveryUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"Gerenciamento de Me"}, description = "Api para gerenciar Me")
@RestController
@RequestMapping(path = "/api/v1/me")
@AllArgsConstructor
@Slf4j
public class MeResource {

    private final MeCreateUpdateUseCase meCreateUpdateUseCase;
    private final MeRecoveryUseCase meRecoveryUseCase;

    @ApiOperation(value = "Grava um novo usuário em nossa base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody @Valid Me me){
        log.info("starting create() of new Me... {}", me.toString());
        return new ResponseEntity<>(
                this.meCreateUpdateUseCase.prepareToCreateOrUpdateMe(me),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza um Me em nossa base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro atualizado com sucesso ou registro não localizado em nossa base de dados"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody @Valid Me me){
        log.info("starting update() a existing Me... {}", me.toString());
        return new ResponseEntity<>(
                this.meCreateUpdateUseCase.prepareToCreateOrUpdateMe(me),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Recupera o registro Me")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @GetMapping(path = "/{uuid}")
    public ResponseEntity<ResponseDto> recoveryMe(@PathVariable(name = "uuid") String uuid){
        log.info("starting recoveryMe() with id {}", uuid);
        return new ResponseEntity<>(this.meRecoveryUseCase.prepareToRecoveryMeById(uuid), HttpStatus.OK);
    }
}
