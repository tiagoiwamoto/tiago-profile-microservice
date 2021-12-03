package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.dataprovider.gateway;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 07:09
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.dataprovider.gateway.dto.ViacepDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "viacep", url = "${app.gateway.viacep}")
public interface ViacepGateway {

    @GetMapping(path = "/{cep}/json/unicode/")
    ResponseEntity<ViacepDto> call(@PathVariable("cep") String cep);

}
