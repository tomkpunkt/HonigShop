package org.hypoport.honig.controller;

import org.hypoport.honig.model.Bestellung;
import org.hypoport.honig.repository.BestellungRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.inject.Inject;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/bestellung")
public class BestellungController {

  @Inject
  BestellungRepository bestellungRepository;

  @RequestMapping(method = POST, produces = {APPLICATION_JSON_VALUE})
  @ResponseStatus(CREATED)
  @ResponseBody
  public void store(@RequestBody final Bestellung bestellung) {
    bestellungRepository.save(bestellung);
  }
}
