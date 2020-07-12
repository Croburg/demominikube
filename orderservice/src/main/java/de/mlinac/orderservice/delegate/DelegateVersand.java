package de.mlinac.orderservice.delegate;

import de.mlinac.orderservice.domain.Bestellung;
import de.mlinac.orderservice.message.MessageSender;
import de.mlinac.orderservice.repository.BestellungRepository;
import de.mlinac.orderservice.service.BestellungService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DelegateVersand implements JavaDelegate {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private BestellungService bestellungService;


    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String bestellnummer = execution.getBusinessKey();
        Bestellung bestellung = bestellungService.getBestellungByBestellnummer(bestellnummer);
        messageSender.sendBestellung("ware-verschicken", bestellung);

    }
}
