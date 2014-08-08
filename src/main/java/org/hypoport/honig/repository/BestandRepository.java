package org.hypoport.honig.repository;

import org.hypoport.honig.model.Bestand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestandRepository extends MongoRepository<Bestand, String> {



}
