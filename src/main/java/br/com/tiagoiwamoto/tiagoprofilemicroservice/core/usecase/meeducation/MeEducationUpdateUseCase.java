package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.meeducation;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 22/10/2021 | 07:20
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.dataprovider.repository.MeRepository;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Education;
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
public class MeEducationUpdateUseCase {

    private final MeRepository meRepository;

    public ResponseDto updateEducationToMe(Education education, String uuid){
        try{
            Optional<Me> optionalMe = this.meRepository.findById(uuid);
            if(optionalMe.isPresent()){
                Me me = optionalMe.get();
                List<Education> educations = me.getEducations();
                Optional<Education> optionalEducation =
                        educations.stream().filter(line -> line.getId().equalsIgnoreCase(education.getId())).findFirst();
                if(optionalEducation.isPresent()){
                    Education educationToUpdate = optionalEducation.get();
                    educations.remove(educationToUpdate);
                    educations.add(education);
                    me.setEducations(educations);
                    me = this.meRepository.save(me);
                    return ApiResponseDto.of(HttpStatus.OK.name(), me.getEducations(), AppMessage.API_SUCCESS);
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
