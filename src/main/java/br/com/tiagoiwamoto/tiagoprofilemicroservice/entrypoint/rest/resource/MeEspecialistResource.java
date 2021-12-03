package br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.resource;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 22/10/2021 | 06:58
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Especialist;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.meespecialist.MeEspecialistCreateUseCase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.meespecialist.MeEspecialistDeleteUseCase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.meespecialist.MeEspecialistUpdateUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"Gerenciamento de Especialidades"}, description = "Api para gerenciar especialidades")
@RestController
@RequestMapping(path = "/api/v1/me")
@AllArgsConstructor
@Slf4j
public class MeEspecialistResource {

    private final MeEspecialistCreateUseCase meEspecialistCreateUseCase;
    private final MeEspecialistUpdateUseCase meEspecialistUpdateUseCase;
    private final MeEspecialistDeleteUseCase meEspecialistDeleteUseCase;

    @ApiOperation(value = "Adiciona uma especialidade")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @PostMapping(path = "/{uuid}/especialists")
    public ResponseEntity<ResponseDto> addEspecialist(
            @RequestBody @Valid Especialist especialist,
            @PathVariable(name = "uuid") String uuid){
        log.info("starting addEspecialist() of new Especialist... {}", especialist.toString());
        return new ResponseEntity<>(
                this.meEspecialistCreateUseCase.addEspecialistToMe(especialist, uuid),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza uma especialidade")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @PutMapping(path = "/{uuid}/especialists")
    public ResponseEntity<ResponseDto> updateEspecialist(
            @RequestBody @Valid Especialist especialist,
            @PathVariable(name = "uuid") String uuid){
        log.info("starting updateEspecialist() of Especialist... {}", especialist.toString());
        return new ResponseEntity<>(
                this.meEspecialistUpdateUseCase.updateEspecialistToMe(especialist, uuid),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Remove uma especialidade")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @DeleteMapping(path = "/{uuid}/especialists/{especialistId}")
    public ResponseEntity<ResponseDto> removeEspecialist(
            @PathVariable(name = "uuid") String uuid,
            @PathVariable(name = "especialistId") String especialistId){
        log.info("starting removeEspecialist() of this Me{} and especialist id {}", uuid, especialistId);
        return new ResponseEntity<>(
                this.meEspecialistDeleteUseCase.prepareToRemoveMeEspecialist(uuid, especialistId),
                HttpStatus.OK);
    }
}
