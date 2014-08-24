package org.hypoport.honig.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bestand")
public class Bestand {

  @Id
  @JsonProperty
  private String id;
  @JsonProperty
  private String name;
  @JsonProperty
  private Integer menge;
  @JsonProperty
  private String image;
  @JsonProperty
  private Integer preis;
  @JsonProperty
  private String beschreibung;

  public Bestand() {
    id = null;
    name = null;
    menge = null;
    image = null;
    preis = null;
    beschreibung = null;
  }

}
