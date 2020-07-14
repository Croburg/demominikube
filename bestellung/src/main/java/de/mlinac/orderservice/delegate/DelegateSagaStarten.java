package de.mlinac.orderservice.delegate;

import de.mlinac.orderservice.service.BestellungService;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DelegateSagaStarten implements JavaDelegate {

    private Logger log = LoggerFactory.getLogger(DelegateSagaStarten.class);
    @Autowired
    private BestellungService bestellungService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String businessKey = execution.getBusinessKey();
        log.info(businessKey);
        bestellungService.sagaStarten("saga-starten", businessKey);
    }
}
