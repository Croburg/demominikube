package de.mlinac.usertask.message;

import de.mlinac.usertask.service.UsertaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@EnableKafka
@Component
public class MessageListener {

    private final Logger log = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private UsertaskService usertaskService;


    public MessageListener() {
    }


    @KafkaListener(topics="usertask",  containerFactory = "mapListenerContainerFactory", groupId="usertask-service")
    public void userTaskListener(Map<String, Object> variables){
        usertaskService.createUserTaskAndStartProcess(variables);

    }


}
