package de.mlinac.versandservice.message;

import de.mlinac.versandservice.domain.Lieferung;
import de.mlinac.versandservice.domain.Status;
import de.mlinac.versandservice.service.VersandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@EnableKafka
@Component
public class MessageListener {

    private final Logger log = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateStatus;

    @Autowired
    private VersandService versandService;


    public MessageListener() {
    }


    @KafkaListener(topics="ware-verschicken",  containerFactory = "lieferungListenerContainerFactory", groupId="versand-service")
    public void lieferung(Lieferung lieferung){
        log.info("Ware verschicken für Bestellung: " + lieferung.getBestellnummer());
        lieferung.setStatus(Status.OFFEN);
        versandService.initialisiereLieferung(lieferung);

    }

    @KafkaListener(topics="versand-stornieren",  containerFactory = "lieferungListenerContainerFactory", groupId="versand-service")
    public void lieferungStornieren(Lieferung lieferung){
        log.info("Lieferung stornieren für Bestellung: " + lieferung.getBestellnummer());
        //Stornieren wenn nicht schon storniert
        versandService.lieferungAbbrechen(lieferung.getBestellnummer());
    }
}
