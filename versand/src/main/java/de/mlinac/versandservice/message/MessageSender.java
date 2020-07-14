package de.mlinac.versandservice.message;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
public class MessageSender {

    private final Logger log = LoggerFactory.getLogger(MessageSender.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateStatus;

    public void sendStatus(String topic, String bestellnummer){
        kafkaTemplateStatus.send(topic, bestellnummer);
        log.info("Message send: '" + topic + "' für Bestellung: " + bestellnummer);
    }
}
