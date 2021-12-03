package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.error;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 05/10/2021 | 20:09
 */

public class InvalidCepException extends RuntimeException{

    public InvalidCepException() {
        super("Check your CEP and try again, we can't found any address.");
    }

}
