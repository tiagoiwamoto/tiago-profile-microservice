package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.mecertificate;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 22/10/2021 | 07:20
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.dataprovider.repository.MeRepository;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Certificate;
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
public class MeCertificateUpdateUseCase {

    private final MeRepository meRepository;

    public ResponseDto updateCertificateToMe(Certificate certificate, String uuid){
        try{
            Optional<Me> optionalMe = this.meRepository.findById(uuid);
            if(optionalMe.isPresent()){
                Me me = optionalMe.get();
                List<Certificate> certificates = me.getCertificates();
                Optional<Certificate> optionalResume =
                        certificates.stream().filter(line -> line.getId().equalsIgnoreCase(certificate.getId())).findFirst();
                if(optionalResume.isPresent()){
                    Certificate CertificateToUpdate = optionalResume.get();
                    certificates.remove(CertificateToUpdate);
                    certificates.add(certificate);
                    me.setCertificates(certificates);
                    me = this.meRepository.save(me);
                    return ApiResponseDto.of(HttpStatus.OK.name(), me.getCertificates(), AppMessage.API_SUCCESS);
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
