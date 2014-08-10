package org.hypoport.honig.controller;

import org.hypoport.honig.model.Bestand;
import org.hypoport.honig.repository.BestandRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.inject.Inject;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/bestand")
public class BestandController {

  @Inject
  BestandRepository bestandRepository;

  @RequestMapping(method = GET, produces = {APPLICATION_JSON_VALUE})
  @ResponseStatus(OK)
  @ResponseBody
  public List<Bestand> getBestaende() {
    return bestandRepository.findAll();
  }
}
