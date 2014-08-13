package org.hypoport.honig.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "bestellung")
public class Bestellung {

  @Id
  public final String id = null;
  public final String email = null;
  public final List<Posten> einzelPosten = new ArrayList<Posten>();
  public final Integer gesamtpreis = null;
  public final Date bestellDatum = new Date();
}
