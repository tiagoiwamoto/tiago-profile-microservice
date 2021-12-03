package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.enums;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 21/10/2021 | 20:13
 */

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TecnologyTypeEnum {

    BACKEND("is-dark", "is-success"),
    FRONTEND("is-dark", "is-info"),
    MOBILE("is-dark", "is-link"),
    DATABASE("is-dark", "has-background-primary-light"),
    CLOUD("is-dark", "has-background-link-light"),
    AGILE("is-dark", "is-primary"),
    BIGDATA("is-dark", "is-warning"),
    DEVOPS("is-dark", "is-danger"),
    OTHERS("is-dark", "");

    private String nameColor;
    private String checkColor;

}
