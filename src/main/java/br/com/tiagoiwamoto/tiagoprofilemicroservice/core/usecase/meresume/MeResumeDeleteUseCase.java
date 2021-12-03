package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.meresume;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 23/10/2021 | 07:50
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Me;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Resume;
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
public class MeResumeDeleteUseCase {

    private final MeCreateUpdateUseCase meCreateUpdateUseCase;
    private final MeRecoveryUseCase meRecoveryUseCase;

    public ResponseDto prepareToRemoveMeResume(String uuid, String resumeId){
        Me me = this.meRecoveryUseCase.validIfRecordExists(uuid);
        Optional<Resume> optionalResume =
                me.getResumes().stream().filter(line -> line.getId().equalsIgnoreCase(resumeId)).findFirst();
        if(optionalResume.isPresent()){
            Resume resume = optionalResume.get();
            me.getResumes().remove(resume);
            this.meCreateUpdateUseCase.prepareToCreateOrUpdateMe(me);
        }else {
            //TODO: lancar exception customizada
            throw new RuntimeException();
        }
        return ApiResponseDto.of(HttpStatus.OK.name(), me, AppMessage.API_SUCCESS);
    }

}
