package org.hypoport.honig.controller;

import org.hypoport.honig.model.Bestellung;
import org.hypoport.honig.repository.BestellungRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/bestellung")
public class BestellungController {

  @Inject
  BestellungRepository bestellungRepository;

  @RequestMapping(method = POST, produces = {APPLICATION_JSON_VALUE})
  @ResponseStatus(CREATED)
  @ResponseBody
  public String create(@RequestBody final Bestellung bestellung) {
    return bestellungRepository.save(bestellung).id;
  }

  @RequestMapping(method = GET, value = "/{bestellnummer}", produces = {APPLICATION_JSON_VALUE})
  @ResponseStatus(FOUND)
  @ResponseBody
  public Bestellung read(@PathVariable String bestellnummer) {
    return bestellungRepository.findOne(bestellnummer);
  }

  @RequestMapping(method = GET, produces = {APPLICATION_JSON_VALUE})
  @ResponseStatus(FOUND)
  @ResponseBody
  public List<Bestellung> readAll() {
    return bestellungRepository.findAll();
  }
}
