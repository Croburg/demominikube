package de.mlinac.orderservice.delegate;

import de.mlinac.orderservice.domain.Bestellung;
import de.mlinac.orderservice.message.MessageSender;
import de.mlinac.orderservice.repository.BestellungRepository;
import de.mlinac.orderservice.service.BestellungService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DelegateZahlung implements JavaDelegate {
    private final Logger log = LoggerFactory.getLogger(DelegateZahlung.class);

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private BestellungService bestellungService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        log.info("DelegateExecution Zahlung für Bestellung: " + execution.getBusinessKey());
        Bestellung bestellung = bestellungService.getBestellungByBestellnummer(execution.getBusinessKey());
        messageSender.sendBestellung("zahlung-durchfuehren", bestellung);




    }
}
