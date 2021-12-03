package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 21/10/2021 | 20:12
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.enums.TecnologyTypeEnum;
import lombok.Data;

@Data
public class Especialist {

    private String id;
    private String name;
    private TecnologyTypeEnum type;

}
