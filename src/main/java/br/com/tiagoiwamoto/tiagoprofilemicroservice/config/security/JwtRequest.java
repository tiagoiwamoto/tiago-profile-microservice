package br.com.tiagoiwamoto.tiagoprofilemicroservice.config.security;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 02/11/2021 | 17:54
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;

}
