package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.meworks;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 23/10/2021 | 07:50
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Me;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Work;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.me.MeCreateUpdateUseCase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.me.MeRecoveryUseCase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.dto.ApiResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.util.AppMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class MeWorkDeleteUseCase {

    private final MeCreateUpdateUseCase meCreateUpdateUseCase;
    private final MeRecoveryUseCase meRecoveryUseCase;

    public ResponseDto prepareToRemoveMeWork(String uuid, String workId){
        Me me = this.meRecoveryUseCase.validIfRecordExists(uuid);
        Optional<Work> optionalResume =
                me.getWorks().stream().filter(line -> line.getId().equalsIgnoreCase(workId)).findFirst();
        if(optionalResume.isPresent()){
            Work work = optionalResume.get();
            me.getWorks().remove(work);
            this.meCreateUpdateUseCase.prepareToCreateOrUpdateMe(me);
        }else {
            //TODO: lancar exception customizada
            throw new RuntimeException();
        }
        return ApiResponseDto.of(HttpStatus.OK.name(), me, AppMessage.API_SUCCESS);
    }

}
