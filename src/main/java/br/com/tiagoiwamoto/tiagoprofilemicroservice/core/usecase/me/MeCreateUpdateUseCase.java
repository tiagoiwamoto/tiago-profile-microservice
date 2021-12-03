package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.me;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 22/10/2021 | 06:33
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.dataprovider.repository.MeRepository;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Me;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.error.MeCreateOrUpdateException;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.dto.ApiResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.util.AppMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@AllArgsConstructor
@Slf4j
public class MeCreateUpdateUseCase {

    private final MeRepository meRepository;
    private final MeRecoveryUseCase meRecoveryUseCase;

    public ResponseDto prepareToCreateOrUpdateMe(Me me){
        log.info("starting prepareToCreateOrUpdateMe() whith body {}", me);
        try{
            HttpStatus httpStatus;
            if(Objects.isNull(me.getId())){
                log.info("We will create a new Me on database");
                me.setCreatedAt(LocalDateTime.now());
                httpStatus = HttpStatus.CREATED;
            }else{
                this.meRecoveryUseCase.validIfRecordExists(me.getId());
                log.info("We will update Me on database");
                me.setUpdatedAt(LocalDateTime.now());
                httpStatus = HttpStatus.OK;
            }
            Me meCreatedOrUpdated = this.meRepository.save(me);
            log.info("Me was updated successfully");
            return ApiResponseDto.of(httpStatus.name(), meCreatedOrUpdated, AppMessage.API_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new MeCreateOrUpdateException();
        }
    }



}
