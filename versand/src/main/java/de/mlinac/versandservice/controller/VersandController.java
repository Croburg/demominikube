package de.mlinac.versandservice.controller;

import de.mlinac.versandservice.domain.Lieferung;
import de.mlinac.versandservice.domain.Status;
import de.mlinac.versandservice.service.VersandService;
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
public class VersandController  {

    private final Logger log = LoggerFactory.getLogger(VersandController.class);
    @Autowired
    private VersandService versandService;

    @RequestMapping(value = "/versand/alle", method = RequestMethod.GET)
    public List<Lieferung> getAll(){
        return versandService.getAll();
    }

    @RequestMapping(value="/verschicken/{bestellnummer}", method = RequestMethod.GET)
    public String lieferungVerschicken(String bestellnummer){
        versandService.lieferungVerschickt(bestellnummer);
        return "Bestellung: '" + bestellnummer +"' verschickt";
    }
    @RequestMapping(value="/verschicken/alle", method = RequestMethod.GET)
    public String lieferungVerschickenAlle(){
        List<Lieferung> lieferungList = versandService.getAlleMitStatus(Status.OFFEN);
        for (Lieferung lieferung : lieferungList){
            try{
                TimeUnit.MILLISECONDS.sleep(300);
                versandService.lieferungVerschickt(lieferung.getBestellnummer());}
            catch(InterruptedException ex){
                log.info(ex.toString());
            }
        }
        return "Alle Lieferungen verschickt";
    }

    @RequestMapping(value="/stornieren/{bestellnummer}", method = RequestMethod.GET)
    public String lieferungStornieren(@PathVariable("bestellnummer") String bestellnummer){
        versandService.lieferungStorniern(bestellnummer);
        return "Bestellung: '" +  bestellnummer + "' nicht verschickt.";    }

    @RequestMapping(value="/stornieren/alle", method = RequestMethod.GET)
    public String lieferungStornierenAlle(){
        List<Lieferung> lieferungList = versandService.getAlleMitStatus(Status.OFFEN);
        for (Lieferung lieferung : lieferungList){
            try{
                TimeUnit.MILLISECONDS.sleep(300);
                versandService.lieferungStorniern(lieferung.getBestellnummer());
            }
            catch(InterruptedException ex){
                log.info(ex.toString());
            }
        }
        return "Alle Lieferungen stroniert";
    }
    @RequestMapping(value="/abbrechen/{bestellnummer}", method = RequestMethod.GET)
    public String lieferungAbbrechen(@PathVariable("bestellnummer") String bestellnummer){
        versandService.lieferungAbbrechen(bestellnummer);
        return "Bestellung: '" +  bestellnummer + "' nicht verschickt.";    }

    @RequestMapping(value="/abbrechen/alle", method = RequestMethod.GET)
    public String lieferungAbbrechenAlle(){
        List<Lieferung> lieferungList = versandService.getAlleMitStatus(Status.OFFEN);
        for (Lieferung lieferung : lieferungList){

                versandService.lieferungAbbrechen(lieferung.getBestellnummer());
        }
        return "Alle Lieferungen storniert";
    }

    /*
    @RequestMapping(value="/abbrechen/alle", method = RequestMethod.GET)
    public String lieferungAbbrechenAlle(){
        List<Lieferung> lieferungList = versandService.getAlleMitStatus(Status.OFFEN);
        for (Lieferung lieferung : lieferungList){
            try{
                TimeUnit.MILLISECONDS.sleep(500);
                versandService.lieferungAbbrechen(lieferung.getBestellnummer());
            }
            catch(InterruptedException ex){
                log.info(ex.toString());
            }
        }
        return "Alle Lieferungen storniert";
    }

     */

    @RequestMapping(value = "/status/{status}", method = RequestMethod.GET)
    public List<Lieferung> getAllStatus(@PathVariable("status") String status){
        if(status == "offen") {
            return versandService.getAlleMitStatus(Status.OFFEN);
        } else if(status == "storniert"){
            return versandService.getAlleMitStatus(Status.STORNIERT);
        } else{
            return versandService.getAlleMitStatus(Status.ABGESCHLOSSEN);
        }
    }




}
