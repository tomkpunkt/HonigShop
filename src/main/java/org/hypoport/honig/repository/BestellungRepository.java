package org.hypoport.honig.repository;

import org.hypoport.honig.model.Bestellung;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestellungRepository extends MongoRepository<Bestellung, String> {

}
