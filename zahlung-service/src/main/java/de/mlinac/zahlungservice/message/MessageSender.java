package de.mlinac.zahlungservice.message;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
@EnableKafka
public class MessageSender {
    private  final  Logger log = Logger.getLogger(MessageSender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateStatus;

    public void sendStatus(String topic, String bestellnummer){
        kafkaTemplateStatus.send(topic, bestellnummer);
        log.info("Message: '" + topic + "' verschickt. Bestellung: " + bestellnummer);

    }
}
