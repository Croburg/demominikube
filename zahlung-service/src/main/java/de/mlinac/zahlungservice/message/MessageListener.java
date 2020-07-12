package de.mlinac.zahlungservice.message;


import de.mlinac.zahlungservice.domain.Zahlung;
import de.mlinac.zahlungservice.service.ZahlungService;
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
    private ZahlungService zahlungService;

    public MessageListener() {
    }


    @KafkaListener(topics="zahlung-durchfuehren",  containerFactory = "zahlungListenerContainerFactory", groupId="zahlung-service")
    public void zahlungInitialisieren(Zahlung zahlung){
        log.info("Message: 'zahlung-durchfuehren' erhalten für Bestellung: " + zahlung.getBestellnummer());
        zahlungService.initialisiereZahlung(zahlung);
    }

    @KafkaListener(topics="zahlung-gutschreiben",  containerFactory = "zahlungListenerContainerFactory", groupId="zahlung-service")
    public void zahlungGutschreiben(Zahlung zahlung){
        log.info("Message: 'zahlung-gutschreiben' erhalten für Bestellung: " + zahlung.getBestellnummer());
        zahlungService.gutschreiben(zahlung.getBestellnummer());
    }


}
