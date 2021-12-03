package br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.resource;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 22/10/2021 | 06:58
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Certificate;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.mecertificate.MeCertificateCreateUseCase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.mecertificate.MeCertificateDeleteUseCase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.mecertificate.MeCertificateUpdateUseCase;
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

@Api(tags = {"Gerenciamento de certificados"}, description = "Api para gerenciar certificados")
@RestController
@RequestMapping(path = "/api/v1/me")
@AllArgsConstructor
@Slf4j
public class MeCertificateResource {

    private final MeCertificateCreateUseCase meCertificateCreateUseCase;
    private final MeCertificateUpdateUseCase meCertificateUpdateUseCase;
    private final MeCertificateDeleteUseCase meCertificateDeleteUseCase;

    @ApiOperation(value = "Adiciona um certificado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @PostMapping(path = "/{uuid}/certificates")
    public ResponseEntity<ResponseDto> addCertificate(
            @RequestBody @Valid Certificate certificate,
            @PathVariable(name = "uuid") String uuid){
        log.info("starting addCertificate() of new Certificate... {}", certificate.toString());
        return new ResponseEntity<>(
                this.meCertificateCreateUseCase.addCertificateToMe(certificate, uuid),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza um certificado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @PutMapping(path = "/{uuid}/certificates")
    public ResponseEntity<ResponseDto> updateCertificate(
            @RequestBody @Valid Certificate work,
            @PathVariable(name = "uuid") String uuid){
        log.info("starting updateCertificate() of Certificate... {}", work.toString());
        return new ResponseEntity<>(
                this.meCertificateUpdateUseCase.updateCertificateToMe(work, uuid),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Remove um certificado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação de registro realizada com sucesso"),
            @ApiResponse(code = 500, message = "Tivemos uma instabilidade no sistema, uma nova tentativa deve ser realizada")
    })
    @DeleteMapping(path = "/{uuid}/certificates/{certificateId}")
    public ResponseEntity<ResponseDto> removeCertificate(
            @PathVariable(name = "uuid") String uuid,
            @PathVariable(name = "certificateId") String workId){
        log.info("starting removeCertificate() of this Me{} and work id {}", uuid, workId);
        return new ResponseEntity<>(
                this.meCertificateDeleteUseCase.prepareToRemoveMeCertificate(uuid, workId),
                HttpStatus.OK);
    }
}
