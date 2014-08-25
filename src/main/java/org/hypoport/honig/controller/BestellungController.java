package org.hypoport.honig.controller;

import org.apache.commons.io.IOUtils;
import org.hypoport.honig.model.Bestellung;
import org.hypoport.honig.repository.BestellungRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.inject.Inject;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.time.LocalDate.now;
import static org.apache.commons.lang3.StringUtils.replace;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Controller
@RequestMapping("/bestellung")
public class BestellungController {

  Logger log = Logger.getLogger(BestellungController.class.getName());

  @Inject
  BestellungRepository bestellungRepository;

  @RequestMapping(method = POST, produces = {APPLICATION_JSON_VALUE})
  @ResponseStatus(CREATED)
  @ResponseBody
  public String create(@RequestBody final Bestellung bestellung) {
    return bestellungRepository.save(bestellung).id;
  }

  @RequestMapping(method = GET, value = "/{bestellnummer}", produces = {APPLICATION_JSON_VALUE})
  @ResponseBody
  public Bestellung read(@PathVariable String bestellnummer) {
    return bestellungRepository.findOne(bestellnummer);
  }

  @RequestMapping(method = PUT, value = "/{bestellnummer}/bezahlt", produces = {APPLICATION_JSON_VALUE})
  @ResponseBody
  public Bestellung setzeAufBezahlt(@PathVariable String bestellnummer) {
    Bestellung bestellung = bestellungRepository.findOne(bestellnummer);
    bestellung.setBezahlt(true);
    return bestellungRepository.save(bestellung);
  }

  @RequestMapping(method = GET, value = "/{bestellnummer}/quittung", produces = {TEXT_HTML_VALUE})
  @ResponseBody
  public String quittung(@PathVariable String bestellnummer) throws IOException {
    Bestellung bestellung = bestellungRepository.findOne(bestellnummer);
    String html = IOUtils.toString(getClass().getResourceAsStream("quittung.html"), "UTF-8");
    html = replace(html, "$BETRAG$", bestellung.gesamtpreis / 100 + ",- &euro;");
    html = replace(html, "$BESTELLNUMMER$", bestellnummer);
    html = replace(html, "$AKTUELLES_DATUM$ ", now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
    return html;
  }

  @RequestMapping(method = GET, produces = {APPLICATION_JSON_VALUE})
  @ResponseBody
  public Iterable<Bestellung> readAll() {
    return bestellungRepository.findAll();
  }

  // Handle all exceptions
  @ExceptionHandler(Exception.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  void handleException(Exception ex) {
    log.log(Level.SEVERE, ex.getMessage(), ex);
  }
}
