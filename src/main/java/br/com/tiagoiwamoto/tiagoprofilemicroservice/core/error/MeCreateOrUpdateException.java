package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.error;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 07:03
 */

public class MeCreateOrUpdateException extends RuntimeException{

    public MeCreateOrUpdateException() {
        super("We have a internal problem, try again later (:");
    }
}
