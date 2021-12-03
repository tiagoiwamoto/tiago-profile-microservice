package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 21/10/2021 | 20:24
 */

import lombok.Data;

@Data
public class Work {

    private String id;
    private String name;
    private String description;
    private String accessUrl;
    private String repositoryUrl;

}
