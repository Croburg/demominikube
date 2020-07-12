package de.mlinac.zahlungservice.controller;

import de.mlinac.zahlungservice.domain.Status;
import de.mlinac.zahlungservice.domain.Zahlung;
import de.mlinac.zahlungservice.service.ZahlungService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class ZahlungController {
    private final Logger log = LoggerFactory.getLogger(ZahlungController.class);

    @Autowired
    private ZahlungService zahlungService;

    @RequestMapping(value="/bezahlen/{bestellnummer}", method = RequestMethod.GET)
    public String bezahlen(@PathVariable("bestellnummer") String bestellnummer){
        zahlungService.bezahlen(bestellnummer);
        return "Bestellung: '" + bestellnummer + "' bezahlt.";
    }

    @RequestMapping(value="/bezahlen/alle", method = RequestMethod.GET)
    public String bezahlen(){
        List<Zahlung> zahlungsList = zahlungService.getAlleMitStatus(Status.OFFEN);
        for(Zahlung zahlung: zahlungsList){

                zahlungService.bezahlen(zahlung.getBestellnummer());}

        return "Alle Zahlungen durchgefuehrt.";
    }

    /*
    @RequestMapping(value="/bezahlen/alle", method = RequestMethod.GET)
    public String bezahlen(){
        List<Zahlung> zahlungsList = zahlungService.getAlleMitStatus(Status.OFFEN);
        for(Zahlung zahlung: zahlungsList){
            try{
                TimeUnit.MILLISECONDS.sleep(300);
                zahlungService.bezahlen(zahlung.getBestellnummer());}
            catch(InterruptedException ex){
                log.info(ex.toString());
            }
        }
        return "Alle Zahlungen durchgefuehrt.";
    }

     */

    @RequestMapping(value="/nichtBezahlen/{bestellnummer}", method = RequestMethod.GET)
    public String nichtBezahlen(@PathVariable("bestellnummer") String bestellnummer){
        zahlungService.abbrechen(bestellnummer);
        return "Bestellung: '" + bestellnummer + "' nicht bezahlt.";
    }
    @RequestMapping(value="/nichtBezahlen/alle", method = RequestMethod.GET)
    public String nichtBezahlenAlle(){
        List<Zahlung> zahlungsList = zahlungService.getAlleMitStatus(Status.OFFEN);
        for(Zahlung zahlung: zahlungsList){
            try{
                TimeUnit.MILLISECONDS.sleep(300);
                zahlungService.abbrechen(zahlung.getBestellnummer());}
            catch(InterruptedException ex){
                log.info(ex.toString());
            }
        }
        return "Alle Zahlungen abgebrochen.";
    }


    @RequestMapping(value="/status/{status}", method = RequestMethod.GET)
    public List<Zahlung> alleMitStatus(@PathVariable("bestellnummer") String status){
        if(status == "offen"){
            return zahlungService.getAlleMitStatus(Status.OFFEN);
        } else if(status =="storniert"){
            return zahlungService.getAlleMitStatus(Status.STORNIERT);
        } else {
            return zahlungService.getAlleMitStatus(Status.ABGESCHLOSSEN);
        }

    }










}
