package br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.resource;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 22/10/2021 | 06:58
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Work;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.meworks.MeWorkCreateUseCase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.meworks.MeWorkDeleteUseCase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.meworks.MeWorkUpdateUseCase;
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

@Api(tags = {"Gerenciamento de trabalhos"}, description = "Api para gerenciar trabalhos")
@RestController
@RequestMapping(path = "/api/v1/me")
@AllArgsConstructor
@Slf4j
public class MeWorkResource {

    private final MeWorkCreateUseCase meWorkCreateUseCase;
    private final MeWorkUpdateUseCase meWorkUpdateUseCase;
    private final MeWorkDeleteUseCase meWorkDeleteUseCase;

    @ApiOperation(value = "Adiciona um trabalho")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @PostMapping(path = "/{uuid}/works")
    public ResponseEntity<ResponseDto> addWork(
            @RequestBody @Valid Work resume,
            @PathVariable(name = "uuid") String uuid){
        log.info("starting addWork() of new Work... {}", resume.toString());
        return new ResponseEntity<>(
                this.meWorkCreateUseCase.addWorkToMe(resume, uuid),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza um trabalho")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @PutMapping(path = "/{uuid}/works")
    public ResponseEntity<ResponseDto> updateWork(
            @RequestBody @Valid Work work,
            @PathVariable(name = "uuid") String uuid){
        log.info("starting updateWork() of Work... {}", work.toString());
        return new ResponseEntity<>(
                this.meWorkUpdateUseCase.updateWorkToMe(work, uuid),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Remove um trabalho")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @DeleteMapping(path = "/{uuid}/works/{workId}")
    public ResponseEntity<ResponseDto> removeWork(
            @PathVariable(name = "uuid") String uuid,
            @PathVariable(name = "workId") String workId){
        log.info("starting removeWork() of this Me{} and work id {}", uuid, workId);
        return new ResponseEntity<>(
                this.meWorkDeleteUseCase.prepareToRemoveMeWork(uuid, workId),
                HttpStatus.OK);
    }
}
