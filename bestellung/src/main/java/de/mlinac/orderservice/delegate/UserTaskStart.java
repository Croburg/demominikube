package de.mlinac.orderservice.delegate;


import de.mlinac.orderservice.domain.Bestellung;
import de.mlinac.orderservice.message.MessageSender;
import de.mlinac.orderservice.service.BestellungService;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.runtime.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserTaskStart  implements TaskListener {

    private Logger log = LoggerFactory.getLogger(UserTaskStart.class);

    @Autowired
    private BestellungService bestellungService;

    @Autowired
    private MessageSender messageSender;


    @Override
    public void notify(DelegateTask delegateTask) {

        String assignee = delegateTask.getAssignee();
        DelegateExecution execution = delegateTask.getExecution();
        String businessKey = execution.getBusinessKey();
        Bestellung bestellung = bestellungService.getBestellungByBestellnummer(businessKey);
        Map<String, Object> variables = new HashMap<>();
        String prozessname = "Bestellung Ueberpruefen";
        String prozessId = "bestellungUeberpruefen";
        String processInstanceId = delegateTask.getProcessInstanceId();
        String taskId = delegateTask.getId();

   //     String taskId = execution.getActivityInstanceId();

        variables.put("prozessname", prozessname);
        variables.put("prozessId", prozessId);
        variables.put("assignee", assignee);
        variables.put("businessKey", businessKey);
        variables.put("bestellnummer", businessKey); //businessKey = bestellnummer
        variables.put("kundeId", bestellung.getKunde().getKundeId());
        variables.put("bestellwert", bestellung.getGesamtwert());
        variables.put("topic", "bestellungUserTask"); // topic für Antwort
        variables.put("formkey", "embedded:/orderservice/forms/genehmigen.html"); //muss noch dynamisch erfolgen
        variables.put("taskId", taskId);
        messageSender.sendUsertask("usertask", variables);
        log.info("UserTask initialisiert für Bestellung: " + businessKey);


    }
}
