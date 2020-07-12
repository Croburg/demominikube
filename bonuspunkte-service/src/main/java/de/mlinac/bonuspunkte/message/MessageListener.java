package de.mlinac.bonuspunkte.message;

import de.mlinac.bonuspunkte.model.Bestellung;
import de.mlinac.bonuspunkte.model.PunkteKonto;
import de.mlinac.bonuspunkte.service.PunkteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@EnableKafka
@Component
public class MessageListener {

    private final Logger log = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateStatus;

    @Autowired
    private PunkteService punkteService;


    public MessageListener() {
    }

    /*
    @KafkaListener(topics="bonuspunkte-gutschreiben",  containerFactory = "bestellungListenerContainerFactory", groupId="bonuspunkte-service")
    public void punkteGutschreiben(Bestellung bestellung){
        log.info("Punkte gutschrieben für Bestellung: " + bestellung.getBestellnummer());
        try{
            TimeUnit.MILLISECONDS.sleep(500);}
        catch(InterruptedException ex){
            log.info(ex.toString());
        }
        punkteService.addPunkte(bestellung);
    }

     */

    @KafkaListener(topics="bonuspunkte-gutschreiben",  containerFactory = "bestellungListenerContainerFactory", groupId="bonuspunkte-service")
    public void punkteGutschreiben(Bestellung bestellung){
        log.info("Punkte gutschrieben für Bestellung: " + bestellung.getBestellnummer());
        punkteService.addPunkte(bestellung);
    }

    @KafkaListener(topics="bonuspunkte-abziehen",  containerFactory = "bestellungListenerContainerFactory", groupId="bonuspunkte-service")
    public void punkteAbziehen(Bestellung bestellung){
        log.info("Punkte abziehen für Bestellung: " + bestellung.getBestellnummer());
        punkteService.abziehenPunkte(bestellung);
    }

}
