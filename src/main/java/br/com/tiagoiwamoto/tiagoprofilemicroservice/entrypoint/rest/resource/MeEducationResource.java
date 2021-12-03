package br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.resource;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 22/10/2021 | 06:58
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Education;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.meeducation.MeEducationCreateUseCase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.meeducation.MeEducationDeleteUseCase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.meeducation.MeEducationUpdateUseCase;
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

@Api(tags = {"Gerenciamento de Education"}, description = "Api para gerenciar instituições de ensino")
@RestController
@RequestMapping(path = "/api/v1/me")
@AllArgsConstructor
@Slf4j
public class MeEducationResource {

    private final MeEducationCreateUseCase meEducationCreateUseCase;
    private final MeEducationUpdateUseCase meEducationUpdateUseCase;
    private final MeEducationDeleteUseCase meEducationDeleteUseCase;

    @ApiOperation(value = "Adiciona uma instituição de ensino")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @PostMapping(path = "/{uuid}/educations")
    public ResponseEntity<ResponseDto> addEducation(
            @RequestBody @Valid Education education,
            @PathVariable(name = "uuid") String uuid){
        log.info("starting addEducation() of new Education... {}", education.toString());
        return new ResponseEntity<>(
                this.meEducationCreateUseCase.addEducationToMe(education, uuid),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza uma instituição de ensino")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @PutMapping(path = "/{uuid}/educations")
    public ResponseEntity<ResponseDto> updateEducation(
            @RequestBody @Valid Education education,
            @PathVariable(name = "uuid") String uuid){
        log.info("starting updateEducation() of Education... {}", education.toString());
        return new ResponseEntity<>(
                this.meEducationUpdateUseCase.updateEducationToMe(education, uuid),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Remove uma instituição de ensino")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @DeleteMapping(path = "/{uuid}/educations/{educationId}")
    public ResponseEntity<ResponseDto> removeEducation(
            @PathVariable(name = "uuid") String uuid,
            @PathVariable(name = "educationId") String educationId){
        log.info("starting removeEducation() of this Me{} and education id {}", uuid, educationId);
        return new ResponseEntity<>(
                this.meEducationDeleteUseCase.prepareToRemoveMeEducation(uuid, educationId),
                HttpStatus.OK);
    }
}
