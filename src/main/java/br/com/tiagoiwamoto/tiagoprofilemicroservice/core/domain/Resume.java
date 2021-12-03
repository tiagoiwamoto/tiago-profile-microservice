package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 21/10/2021 | 20:22
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.enums.ResumeTypeEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Resume {

    private String id;
    private String language;
    private String url;
    private LocalDate updateDate;
    private ResumeTypeEnum type;

}
