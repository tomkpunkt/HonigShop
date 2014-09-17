package org.hypoport.honig.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Document(collection = "bestellung")
public class Bestellung {

  @Id
  public final String id = randomAlphanumeric(6).toUpperCase();
  public final String email = null;
  public final List<Posten> einzelPosten = new ArrayList<>();
  public final Integer gesamtpreis = null;
  public final Date bestellDatum = new Date();
  private boolean bezahlt = false;
  private boolean emailVerschickt = false;
  public final String abholungIn = null;

  public boolean isBezahlt() {
    return bezahlt;
  }

  public void setBezahlt(boolean bezahlt) {
    this.bezahlt = bezahlt;
  }

  public boolean isEmailVerschickt() {
    return emailVerschickt;
  }

  public void setEmailVerschickt(boolean emailVerschickt) {
    this.emailVerschickt = emailVerschickt;
  }
}
