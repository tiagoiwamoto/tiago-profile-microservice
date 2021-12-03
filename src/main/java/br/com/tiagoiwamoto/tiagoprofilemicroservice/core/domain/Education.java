package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 21/10/2021 | 20:10
 */

import lombok.Data;

import java.time.LocalDate;

@Data
public class Education {

    private String id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isCurrent;

}
