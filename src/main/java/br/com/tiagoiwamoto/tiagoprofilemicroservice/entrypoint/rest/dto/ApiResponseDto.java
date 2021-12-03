package br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.dto;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 07:28
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.config.rest.ResponseDto;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class ApiResponseDto<T> extends ResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7059932410249377262L;

    private T data;
    private String message;

    private ApiResponseDto(String code, T data, String message) {
        super(code, LocalDateTime.now());
        this.data = data;
        this.message = message;
    }

    public static <T> ApiResponseDto<T> of(String code, T data, String message){
        return new ApiResponseDto<>(code, data, message);
    }

}
