package de.mlinac.usertask.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@EnableKafka
public class MessageSender {

    private final Logger log = LoggerFactory.getLogger(MessageSender.class);

    @Autowired
    private KafkaTemplate<String, Map<String, Object>> kafkaTemplateMap;


    public void sendUsertask(String topic, Map<String, Object> variables){
        kafkaTemplateMap.send(topic, variables);

    }
}
