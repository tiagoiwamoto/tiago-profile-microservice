package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.meespecialist;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 22/10/2021 | 07:20
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.dataprovider.repository.MeRepository;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Specialist;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Me;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.dto.ApiResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.util.AppMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
@AllArgsConstructor
public class MeEspecialistCreateUseCase {

    private final MeRepository meRepository;

    public ResponseDto addEspecialistToMe(Specialist specialist, String uuid){
        try{
            Optional<Me> optionalMe = this.meRepository.findById(uuid);
            if(optionalMe.isPresent()){
                Me me = optionalMe.get();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
                String generatedId = UUID.nameUUIDFromBytes(specialist.getName().concat(dtf.format(LocalDateTime.now()))
                        .getBytes(StandardCharsets.UTF_8)).toString();
                specialist.setId(generatedId);
                List<Specialist> specialists = me.getSpecialists() == null ? new ArrayList<>() : me.getSpecialists();
                specialists.add(specialist);
                me.setSpecialists(specialists);
                me = this.meRepository.save(me);
                return ApiResponseDto.of(HttpStatus.CREATED.name(), me.getSpecialists(), AppMessage.API_SUCCESS);
            }else {
                //TODO: lancar exception que nao existe o Me
                throw new RuntimeException();
            }
        }catch (Exception e){
            //TODO: lancar exception
            throw new RuntimeException();
        }
    }
}
