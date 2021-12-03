package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.error;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 08/10/2021 | 06:18
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.util.AppMessage;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException() {
        super(AppMessage.Error.USER_ALREADY_EXISTS);
    }
}
