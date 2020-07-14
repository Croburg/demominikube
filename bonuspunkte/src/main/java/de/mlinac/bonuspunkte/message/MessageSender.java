package de.mlinac.bonuspunkte.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@EnableKafka
public class MessageSender {

    private final Logger log = LoggerFactory.getLogger(MessageSender.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateStatus;

    public void sendStatus(String topic, String bestellnummer){

        try{
            TimeUnit.MILLISECONDS.sleep(200);
            kafkaTemplateStatus.send(topic, bestellnummer);
        }
        catch(InterruptedException ex){
            log.info(ex.toString());
        }
    }
}
