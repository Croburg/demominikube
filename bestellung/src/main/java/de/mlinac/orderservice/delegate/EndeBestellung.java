package de.mlinac.orderservice.delegate;

import de.mlinac.orderservice.domain.Status;
import de.mlinac.orderservice.service.BestellungService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EndeBestellung implements ExecutionListener {

    private final Logger log = LoggerFactory.getLogger(EndeBestellung.class);

    @Autowired
    private BestellungService bestellungService;

    @Override
    public void notify(DelegateExecution execution) throws Exception {

        String bestellnummer = execution.getBusinessKey();
        bestellungService.statusBestellung(bestellnummer,Status.ABGESCHLOSSEN);
        log.info("Bestellung abgeschlossen. Bestellung: " + bestellnummer);


    }
}
