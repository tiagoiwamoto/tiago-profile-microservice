package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.viacep;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 05/10/2021 | 19:43
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.dataprovider.gateway.ViacepGateway;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.dataprovider.gateway.dto.ViacepDto;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.error.InvalidCepException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
@Slf4j
public class ViacepUsecase {

    private final ViacepGateway viacepGateway;

    public ViacepDto connectToViacep(String cep){

        ResponseEntity<ViacepDto> viacepResponse = this.viacepGateway.call(cep);
        log.info("Connection with via cep is completed. response -> {}", viacepResponse.toString());
        if(Objects.isNull(Objects.requireNonNull(viacepResponse.getBody()).getCep())){
            log.error("Can't found the cep -> {}", cep);
            throw new InvalidCepException();
        }

        return viacepResponse.getBody();
    }


}
