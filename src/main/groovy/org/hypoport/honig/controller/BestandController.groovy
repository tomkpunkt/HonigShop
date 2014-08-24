package org.hypoport.honig.controller;

import org.hypoport.honig.model.Bestand
import org.hypoport.honig.repository.BestandRepository;
import org.hypoport.honig.repository.BestellungRepository;
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.inject.Inject
import java.util.logging.Level
import java.util.logging.Logger

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/bestand")
public class BestandController {

    Logger log = Logger.getLogger(BestandController.class.getName())


    @Inject
    BestandRepository bestandRepository;

    @Inject
    BestellungRepository bestellungRepository;

    @RequestMapping(method = GET, produces = [APPLICATION_JSON_VALUE])
    @ResponseStatus(OK)
    @ResponseBody
    public List<Bestand> getBestaende() {
        bestandRepository
                .findAll()
                .collect { subtrahiereVerkaufte(it) }
    }

    @RequestMapping(method = GET, value = "/{id}", produces = [APPLICATION_JSON_VALUE])
    @ResponseStatus(OK)
    @ResponseBody
    public Bestand getBestand(@PathVariable("id") String id) {
        subtrahiereVerkaufte(bestandRepository.findOne(id))
    }

    private Bestand subtrahiereVerkaufte(Bestand dbo) {
        def verkauft = bestellungRepository
                .findAllWithArtikelId(dbo.id)
                .collect { it.einzelPosten }
                .flatten().findAll { it.artikelId == dbo.id && it.anzahl > 0 }
                .sum { it.anzahl }

        new Bestand(
                id: dbo.id,
                name: dbo.name,
                menge: dbo.menge - verkauft,
                image: dbo.image,
                preis: dbo.preis,
                beschreibung: dbo.beschreibung)
    }

    // Handle all exceptions
    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    void handleException(Exception ex) {
        log.log(Level.SEVERE, ex.getMessage(), ex);
    }
}
