package de.mlinac.usertask.service;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UsertaskService {

    private Logger log = LoggerFactory.getLogger(UsertaskService.class);

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;


    public void createUserTaskAndStartProcess(Map<String, Object> variables) {
        List<ProcessDefinition> processes = repositoryService.createProcessDefinitionQuery().processDefinitionId(variables.get("prozessId").toString()).list();
        if(processes.isEmpty()) {
            BpmnModelInstance modelInstance = Bpmn.createProcess()
                    .name(variables.get("prozessname").toString())
                    .id(variables.get("prozessId").toString())
                    .executable()
                    .startEvent()
                    .userTask()
                    .name(variables.get("prozessname").toString())
                    .camundaFormKey(variables.get("formkey").toString())
                    .camundaAssignee(variables.get("assignee").toString())
                    .endEvent()
                    .camundaExecutionListenerDelegateExpression("end", "${delegateTaskCompleted}")
                    .done();
            DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
            deploymentBuilder.addModelInstance(variables.get("prozessname").toString() + ".bpmn", modelInstance);
            deploymentBuilder.deploy();
            log.info("UserTask-Prozess gestartet für" + variables.get("prozessname").toString());
        } else {
            log.info("Prozess ist bereits vorhanden");
        }
        try {
            variables.forEach((k,v) -> log.info(k +" " +  v));
            runtimeService.startProcessInstanceByKey("bestellungUeberpruefen", variables.get("businessKey").toString(), variables);
            log.info("Usertask initialisiert.");
        } catch (Exception ex){
            log.info(ex.getMessage());
        }

    }
    //variables.get("prozessId").toString()

    /*
    public boolean processNochNichtVorhande(Map<String, Object> variables){

    }

     */

    public void startUserTaskOfProcess(Map<String, Object> variables){

    }
}
