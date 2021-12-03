package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.dataprovider.repository;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 07:02
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrName(String username, String name);

}
