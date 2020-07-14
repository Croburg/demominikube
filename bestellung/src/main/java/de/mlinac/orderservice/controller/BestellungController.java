package de.mlinac.orderservice.controller;

import de.mlinac.orderservice.domain.Bestellung;
import de.mlinac.orderservice.service.BestellungService;
import org.camunda.bpm.engine.migration.MigrationPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BestellungController {

    @Autowired
    private BestellungService bestellungService;


    @RequestMapping(value = "/bestellung/alle", method = RequestMethod.GET)
    public List<Bestellung> alleBestellungen(){
        return bestellungService.getAlleBestellungen();
    }

    @RequestMapping(value = "/bestellung/{bestellnummer}", method = RequestMethod.GET)
    public Bestellung alleBestellungen(@PathVariable("bestellnummer") String bestellnummer){
        return bestellungService.getBestellungByBestellnummer(bestellnummer);
    }

    @RequestMapping(value = "/migrate", method = RequestMethod.GET)
    public MigrationPlan migrate(){
       return  bestellungService.migrate();
    }


}
