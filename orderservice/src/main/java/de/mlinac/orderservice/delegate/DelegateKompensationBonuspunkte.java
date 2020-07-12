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
public class DelegateKompensationBonuspunkte implements JavaDelegate {

    @Autowired
    private BestellungService bestellungService;

    @Autowired
    private MessageSender messageSender;


    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String bestellnummer = execution.getBusinessKey();
        Bestellung bestellung = bestellungService.getBestellungByBestellnummer(bestellnummer);

        messageSender.sendBestellung("bonuspunkte-abziehen", bestellung);

    }
}
