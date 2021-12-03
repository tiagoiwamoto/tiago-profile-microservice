package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.dataprovider.repository;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 22/10/2021 | 06:35
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain.Me;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeRepository extends MongoRepository<Me, String> {
}
