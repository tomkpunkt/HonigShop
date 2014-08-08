package org.hypoport.honig.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bestand")
public class Bestand {

  @Id
  public final String id = null;
  public final String name = null;
  public final Integer menge = null;
  public final String image = null;
  public final Integer preis = null;

}
