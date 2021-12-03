package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.meresume;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 22/10/2021 | 07:20
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.dataprovider.repository.MeRepository;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Me;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Resume;
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
public class MeResumeCreateUseCase {

    private final MeRepository meRepository;

    public ResponseDto addResumeToMe(Resume resume, String uuid){
        try{
            Optional<Me> optionalMe = this.meRepository.findById(uuid);
            if(optionalMe.isPresent()){
                Me me = optionalMe.get();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
                String generatedId = UUID.nameUUIDFromBytes(resume.getUrl().concat(dtf.format(LocalDateTime.now()))
                        .getBytes(StandardCharsets.UTF_8)).toString();
                resume.setId(generatedId);
                List<Resume> resumeList = me.getResumes() == null ? new ArrayList<>() : me.getResumes();
                resumeList.add(resume);
                me.setResumes(resumeList);
                me = this.meRepository.save(me);
                return ApiResponseDto.of(HttpStatus.CREATED.name(), me.getResumes(), AppMessage.API_SUCCESS);
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
