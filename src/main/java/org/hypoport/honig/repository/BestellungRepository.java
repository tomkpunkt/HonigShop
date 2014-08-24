package org.hypoport.honig.repository;

import org.hypoport.honig.model.Bestellung;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BestellungRepository extends CrudRepository<Bestellung, String> {

  @Query("{'einzelPosten.artikelId':?0}")
  List<Bestellung> findAllWithArtikelId(String id);

}
