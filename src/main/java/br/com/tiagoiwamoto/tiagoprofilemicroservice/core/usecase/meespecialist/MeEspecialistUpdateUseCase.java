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

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class MeEspecialistUpdateUseCase {

    private final MeRepository meRepository;

    public ResponseDto updateEspecialistToMe(Specialist specialist, String uuid){
        try{
            Optional<Me> optionalMe = this.meRepository.findById(uuid);
            if(optionalMe.isPresent()){
                Me me = optionalMe.get();
                List<Specialist> specialists = me.getSpecialists();
                Optional<Specialist> optionalEspecialist =
                        specialists.stream().filter(line -> line.getId().equalsIgnoreCase(specialist.getId())).findFirst();
                if(optionalEspecialist.isPresent()){
                    Specialist specialistToUpdate = optionalEspecialist.get();
                    specialists.remove(specialistToUpdate);
                    specialists.add(specialist);
                    me.setSpecialists(specialists);
                    me = this.meRepository.save(me);
                    return ApiResponseDto.of(HttpStatus.OK.name(), me.getSpecialists(), AppMessage.API_SUCCESS);
                }else {
                    //TODO: lancar excetpion que nao localizou o education para atualizar
                    throw new RuntimeException();
                }
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
