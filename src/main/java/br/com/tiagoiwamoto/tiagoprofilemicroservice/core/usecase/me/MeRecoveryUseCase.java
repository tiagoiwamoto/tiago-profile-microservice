package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.me;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 23/10/2021 | 07:37
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.dataprovider.repository.MeRepository;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Me;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.dto.ApiResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.util.AppMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Component
public class MeRecoveryUseCase {

    private final MeRepository meRepository;

    public ResponseDto prepareToRecoveryMeById(String id){
        try{
            Optional<Me> optionalMe = this.meRepository.findById(id);
            if(optionalMe.isEmpty()){
                log.info("The user with id {} not found on database", id);
                //TODO: create a custom exception
                throw new RuntimeException();
            }else{
                return ApiResponseDto.of(HttpStatus.OK.name(), optionalMe.get(), AppMessage.API_SUCCESS);
            }
        }catch (Exception e){
            //TODO: create a custom exception
            throw new RuntimeException();
        }
    }

    public Me validIfRecordExists(String id){
        Optional<Me> optionalMe = this.meRepository.findById(id);
        if(optionalMe.isEmpty()){
            log.info("The user with id {} not found on database", id);
            //TODO: create a custom exception
            throw new RuntimeException();
        }else{
            return optionalMe.get();
        }
    }

}
