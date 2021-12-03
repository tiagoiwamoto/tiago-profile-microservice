package br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.resource;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 22/10/2021 | 06:58
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Resume;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.meresume.MeResumeCreateUseCase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.meresume.MeResumeDeleteUseCase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.meresume.MeResumeUpdateUseCase;
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

@Api(tags = {"Gerenciamento de Cvs"}, description = "Api para gerenciar curriculum")
@RestController
@RequestMapping(path = "/api/v1/me")
@AllArgsConstructor
@Slf4j
public class MeResumeResource {

    private final MeResumeCreateUseCase meResumeCreateUseCase;
    private final MeResumeUpdateUseCase meResumeUpdateUseCase;
    private final MeResumeDeleteUseCase meResumeDeleteUseCase;

    @ApiOperation(value = "Adiciona um curriculum")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @PostMapping(path = "/{uuid}/resume")
    public ResponseEntity<ResponseDto> addResume(
            @RequestBody @Valid Resume resume,
            @PathVariable(name = "uuid") String uuid){
        log.info("starting addResume() of new Resume... {}", resume.toString());
        return new ResponseEntity<>(
                this.meResumeCreateUseCase.addResumeToMe(resume, uuid),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza um curriculum")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @PutMapping(path = "/{uuid}/resume")
    public ResponseEntity<ResponseDto> updateResume(
            @RequestBody @Valid Resume resume,
            @PathVariable(name = "uuid") String uuid){
        log.info("starting updateResume() of Resume... {}", resume.toString());
        return new ResponseEntity<>(
                this.meResumeUpdateUseCase.updateResumeToMe(resume, uuid),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Remove um curriculum")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @DeleteMapping(path = "/{uuid}/resume/{resumeId}")
    public ResponseEntity<ResponseDto> removeResume(
            @PathVariable(name = "uuid") String uuid,
            @PathVariable(name = "resumeId") String resumeId){
        log.info("starting removeResume() of this Me{} and resume id {}", uuid, resumeId);
        return new ResponseEntity<>(
                this.meResumeDeleteUseCase.prepareToRemoveMeResume(uuid, resumeId),
                HttpStatus.OK);
    }
}
