package br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 07:29
 */

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public abstract class ResponseDto implements Serializable {

    private static final long serialVersionUID = 3144506728341850047L;

    private String code;
    private LocalDateTime timestamp;

    protected ResponseDto(String code, LocalDateTime timestamp) {
        this.code = code;
        this.timestamp = timestamp;
    }
}
