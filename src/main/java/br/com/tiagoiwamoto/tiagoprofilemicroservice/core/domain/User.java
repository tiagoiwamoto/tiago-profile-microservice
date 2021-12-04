package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 07:00
 */

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class User {

    @Id
    private String id;
    private String name;
    private String username;
    private String password;
    private String cep;
    private String address;
    private String neighborhood;
    private String city;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime removedAt;

    public User() {}

    public static User build() {
        return new User();
    }

}
