package de.mlinac.orderservice.message;

import de.mlinac.orderservice.domain.Bestellung;
import de.mlinac.orderservice.domain.Status;
import de.mlinac.orderservice.service.BestellungService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@EnableKafka
@Component
public class MessageListener {

    private final Logger log = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private BestellungService bestellungService;


    public MessageListener() {
    }


    @KafkaListener(topics="checkout1",  containerFactory = "bestellungListenerContainerFactory", groupId="bestellung-service")
    public void bestellung1(Bestellung bestellung){
        log.info("*");
        log.info("*");
        bestellung.calculateGesamtwert();
        log.info("Checkout erhalten. Bestellung: " + bestellung.getBestellnummer()+ " initialisieren.");
        try{
            bestellungService.createBestellung(bestellung);
            bestellungService.startProcess("bestellung-eingegangen1", bestellung.getBestellnummer(), bestellung.getGesamtwert());
        } catch (Exception exc){
            log.info(exc.toString());
        }

    }

    @KafkaListener(topics="checkout2",  containerFactory = "bestellungListenerContainerFactory", groupId="bestellung-service")
    public void bestellung2(Bestellung bestellung){
        log.info("*");
        log.info("*");
        bestellung.calculateGesamtwert();
        log.info("Checkout erhalten. Bestellung: " + bestellung.getBestellnummer()+ " initialisieren.");
        try{
            bestellungService.createBestellung(bestellung);
            bestellungService.startProcess("bestellung-eingegangen2", bestellung.getBestellnummer(), bestellung.getGesamtwert());
        } catch (Exception exc){
            log.info(exc.toString());
        }

    }

    @KafkaListener(topics="checkout3",  containerFactory = "bestellungListenerContainerFactory", groupId="bestellung-service")
    public void bestellung3(Bestellung bestellung){

        log.info("*");
        log.info("*");
        bestellung.setGesamtwert(11000);
        log.info(bestellung.toString());
        log.info("Checkout erhalten. Bestellung: " + bestellung.getBestellnummer()+ " initialisieren.");
        try{
            bestellungService.createBestellung(bestellung);
            bestellungService.startProcess("bestellung-eingegangen3", bestellung.getBestellnummer(), bestellung.getGesamtwert());
        } catch (Exception exc){
            log.info(exc.toString());
        }

    }



    @KafkaListener(topics="zahlung-erhalten",  containerFactory = "statusListenerContainerFactory", groupId="bestellung-service")
    public void bestellungZahlungErhalten(String bestellnummer){
        log.info("Zahlung erhalten. Bestellnummer : " + bestellnummer);
      if(bestellungService.bestellungExistiert(bestellnummer)){
          bestellungService.zahlungStatusBestellung(bestellnummer ,Status.ABGESCHLOSSEN);
          bestellungService.processEventRegistrieren("zahlung-erhalten", bestellnummer);
      } else {
          log.info("Bestellung: "  + bestellnummer + " existiert nicht. ZAHLUNG-ERHALTEN");
      }

    }

    @KafkaListener(topics="zahlung-fehlgeschlagen",  containerFactory = "statusListenerContainerFactory", groupId="bestellung-service")
    public void bestellungZahlungFehlgeschlagen(String bestellnummer){
        log.info("Zahlung fehlgeschlagen. Bestellnummer : " + bestellnummer);
        if(bestellungService.bestellungExistiert(bestellnummer)) {
            bestellungService.zahlungStatusBestellung(bestellnummer, Status.STORNIERT);
            bestellungService.processEventRegistrieren("saga-starten", bestellnummer);
        } else{
            log.info("Bestellung: "  + bestellnummer + " existiert nicht. ZAHLUNG-FEHLGESCHLAGEN");
        }

    }
    @KafkaListener(topics="zahlung-gutgeschrieben",  containerFactory = "statusListenerContainerFactory", groupId="bestellung-service")
    public void bestellungZahlungGutgeschrieben(String bestellnummer){
        log.info("Zahlung gutgeschrieben. Bestellnummer : " + bestellnummer);
        if(bestellungService.bestellungExistiert(bestellnummer)) {
            bestellungService.zahlungStatusBestellung(bestellnummer, Status.STORNIERT);
        } else{
            log.info("Bestellung: "  + bestellnummer + " existiert nicht. ZAHLUNG-GUTGESCHRIEBEN");
        }
    }

    @KafkaListener(topics="lieferung-verschickt",  containerFactory = "statusListenerContainerFactory", groupId="bestellung-service")
    public void bestellungWareVersandt(String bestellnummer){
        log.info("Lieferung verschickt. Bestellnummer : " + bestellnummer);
        if(bestellungService.bestellungExistiert(bestellnummer)) {
            bestellungService.lieferungStatusBestellung(bestellnummer, Status.ABGESCHLOSSEN);
            bestellungService.processEventRegistrieren("lieferung-verschickt", bestellnummer);
        } else{
            log.info("Bestellung: "  + bestellnummer + " existiert nicht. LIEFERUNG-VERSCHICKT");
        }
    }

    @KafkaListener(topics="lieferung-fehlgeschlagen",  containerFactory = "statusListenerContainerFactory", groupId="bestellung-service")
    public void bestellungVersandFehlgeschlagen(String bestellnummer){
        log.info("Lieferung fehlgeschlagen. Bestellnummer : " + bestellnummer);
        if(bestellungService.bestellungExistiert(bestellnummer)) {
            bestellungService.lieferungStatusBestellung(bestellnummer, Status.STORNIERT);
            bestellungService.processEventRegistrieren("saga-starten", bestellnummer);
        } else{
            log.info("Bestellung: "  + bestellnummer + " existiert nicht LIEFERUNG-FEHLGESCHLAGEN");
        }
    }

    @KafkaListener(topics="lieferung-storniert",  containerFactory = "statusListenerContainerFactory", groupId="bestellung-service")
    public void bestellungVersandStorniert(String bestellnummer){
        log.info("Lieferung storniert. Bestellnummer : " + bestellnummer);
        if(bestellungService.bestellungExistiert(bestellnummer)) {
            bestellungService.lieferungStatusBestellung(bestellnummer, Status.STORNIERT);
        } else {
            log.info("Bestellung: "  + bestellnummer + " existiert nicht. LIEFERUNG-STORNIERT");
        }
    }

    @KafkaListener(topics="bonuspunkte-gutgeschrieben",  containerFactory = "statusListenerContainerFactory", groupId="bestellung-service")
    public void bestellungPunkteGutgeschrieben(String bestellnummer){
        log.info("Bonuspunkte gutgeschrieben. Bestellnummer : " + bestellnummer);
        if(bestellungService.bestellungExistiert(bestellnummer)) {
            bestellungService.bonuspunkteStatusBestellung(bestellnummer, Status.ABGESCHLOSSEN);
            bestellungService.processEventRegistrieren("bonuspunkte-gutgeschrieben", bestellnummer);
        } else {
            log.info("Bestellung: "  + bestellnummer + " existiert nicht. BONUSPUNKTE-GUTGESCHRIEBEN");
        }

    }
    @KafkaListener(topics="bonuspunkte-abgezogen",  containerFactory = "statusListenerContainerFactory", groupId="bestellung-service")
    public void bestellungPunkteAbgezogen(String bestellnummer){
        log.info("Bonuspunkte abgezogen. Bestellnummer : " + bestellnummer);
        if(bestellungService.bestellungExistiert(bestellnummer)) {
            bestellungService.bonuspunkteStatusBestellung(bestellnummer, Status.STORNIERT);
        } else {
            log.info("Bestellung: "  + bestellnummer + " existiert nicht. BONUSPUNKTE-ABGEZOGEN");
        }
    }

    @KafkaListener(topics="bestellungUserTask",  containerFactory = "mapListenerContainerFactory", groupId="bestellung-service")
    public void userTaskAbgeschlossen(Map<String, Object> map){
        log.info("Abgeschlossener Usertask erhalten : " + map.get("bestellnummer").toString());
        Map<String, Object> variables = new HashMap<>();
        variables.put("genehmigen", map.get("genehmigen"));
        log.info(map.get("genehmigen").toString());
        String bestellnummer = map.get("bestellnummer").toString();
        bestellungService.completeUserTask(map.get("taskId").toString(), variables);
    }
}
