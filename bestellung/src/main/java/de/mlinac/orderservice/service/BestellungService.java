package de.mlinac.orderservice.service;

import de.mlinac.orderservice.domain.Artikel;
import de.mlinac.orderservice.domain.Artikelwahl;
import de.mlinac.orderservice.domain.Bestellung;
import de.mlinac.orderservice.domain.Status;
import de.mlinac.orderservice.repository.ArtikelRepository;
import de.mlinac.orderservice.repository.ArtikelwahlRepository;
import de.mlinac.orderservice.repository.BestellungRepository;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.migration.MigrationPlan;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
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
public class BestellungService {

    private final Logger log = LoggerFactory.getLogger(BestellungService.class);
    @Autowired
    private BestellungRepository bestellungRepository;
    @Autowired
    private ArtikelRepository artikelRepository;
    @Autowired
    private ArtikelwahlRepository artikelwahlRepository;

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private FormService formService;
    @Autowired
    private TaskService taskService;

    private ProcessInstance processInstance;



    public Bestellung getBestellungByBestellnummer(String bestellnummer){
       /* List<Bestellung> bestellungList= bestellungRepository.findAll();
        for(Bestellung bestellung : bestellungList){
            if(bestellung.getBestellnummer() == bestellnummer){

                return bestellung;
            }
        }
        return null; */
       return bestellungRepository.findByBestellnummer(bestellnummer);
    }



    public void createBestellung(Bestellung bestellung) {
        if (bestellungRepository.existsById(bestellung.getId())) {
            log.info("Bestellung bereits vorhanden. Id: " + bestellung.getId());
        } else {
            for (Artikelwahl artikelwahl : bestellung.getArtikelwahl()) {
                artikelRepository.save(artikelwahl.getArtikel());
                artikelwahlRepository.save(artikelwahl);
                bestellung.setGesamtstatus(Status.OFFEN);
                bestellung.setZahlungstatus(Status.OFFEN);
                bestellung.setLieferungstatus(Status.OFFEN);
                bestellung.setBonuspunktestatus(Status.OFFEN);
            }
            bestellungRepository.save(bestellung);
        }
    }

    public void startProcess(String message, String bestellnummer, double bestellwert){
        Map<String, Object> variables = new HashMap<>();
        variables.put("bestellwert", bestellwert);
        try {
            processInstance = this.runtimeService.startProcessInstanceByMessage(message, bestellnummer, variables);
        } catch (Exception ex){
            log.info(ex.toString());
        }
        log.info("Für Bestellung: " + bestellnummer + ". wurde Prozess gestartet: " + processInstance.getProcessInstanceId());
    }
    public boolean processEventRegistrieren(String message, String businessKey){
        boolean exists = bestellungRepository.existsByBestellnummer(businessKey);
        if(exists){
            runtimeService.createMessageCorrelation(message)
                    .processInstanceBusinessKey(businessKey)
                    .correlate();
            log.info("Event: " + message + " zu Prozess zugeordnet.");}
        else{
            log.info("Bestellung: " + businessKey+" nicht vorhanden");
        }
        return exists;
    }

    public void sagaStarten(String message, String businessKey) {
        try {
          //  runtimeService.createMessageCorrelation(message)
         //           .processInstanceBusinessKey(businessKey)
          //          .correlateStartMessage();

        log.info("Event: " + message + " zu Prozess zugeordnet.");
    } catch (Exception exc) {
            log.info(exc.toString());
        }
    }

    public void statusBestellung(String bestellnummer, Status status){
        Bestellung bestellung = bestellungRepository.findByBestellnummer(bestellnummer);
        bestellung.setGesamtstatus(status);
        bestellungRepository.save(bestellung);
        log.info("Gesamtatus: " +  status + " für Bestellung: " + bestellnummer);
    }
    public void zahlungStatusBestellung(String bestellnummer, Status status){
        Bestellung bestellung = bestellungRepository.findByBestellnummer(bestellnummer);
        bestellung.setZahlungstatus(status);
        bestellungRepository.save(bestellung);
        log.info("Status Zahlung: " +  status + " für Bestellung: " + bestellnummer);
    }
    public void lieferungStatusBestellung(String bestellnummer, Status status){
        Bestellung bestellung = bestellungRepository.findByBestellnummer(bestellnummer);
        bestellung.setLieferungstatus(status);
        bestellungRepository.save(bestellung);
        log.info("Status Lieferung: " +  status + " für Bestellung: " + bestellnummer);
    }
    public void bonuspunkteStatusBestellung(String bestellnummer, Status status){
        Bestellung bestellung = bestellungRepository.findByBestellnummer(bestellnummer);
        bestellung.setBonuspunktestatus(status);
        bestellungRepository.save(bestellung);
        log.info("Status Bonuspunkte: " +  status + " für Bestellung: " + bestellnummer);
    }

    public boolean bestellungExistiert(String bestellnummer){
        return bestellungRepository.existsByBestellnummer(bestellnummer);
    }

    public List<Bestellung> getAlleBestellungen(){
        return bestellungRepository.findAll();
    }

    public MigrationPlan migrate(){

        ProcessDefinition bestellprozess1 = repositoryService.createProcessDefinitionQuery().processDefinitionKey("bestellprozess_2").singleResult();
        ProcessDefinition bestellprozess2 = repositoryService.createProcessDefinitionQuery().processDefinitionKey("bestellprozess_3").singleResult();
        log.info(bestellprozess1.getId());
        log.info(bestellprozess2.getId());


        MigrationPlan migrationPlan = runtimeService.createMigrationPlan(bestellprozess1.getId(), bestellprozess2.getId())
                .mapEqualActivities()
                .updateEventTriggers()
              /*  .mapActivities("startEvent", "startEvent")
                .mapActivities("zahlungTask", "zahlungTask")
                .mapActivities()

               */
                .build();
                runtimeService.newMigration(migrationPlan)
                        .processInstanceQuery(runtimeService.createProcessInstanceQuery().processDefinitionId(bestellprozess1.getId()))
                        .execute();
        return  migrationPlan;
    }

    public void completeUserTask(String taskId, Map<String, Object> variables){
        try {
            taskService.complete(taskId, variables);
            log.info("UserTask abgeschlossen.Bestellung: " + variables.get("bestellnummer").toString());
        } catch (Exception exc){
            log.info(exc.getMessage() + "Task nicht vorhanden");
        }
    }



}
