package de.mlinac.orderservice.delegate;

import de.mlinac.orderservice.domain.Bestellung;
import de.mlinac.orderservice.domain.Status;
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
public class DelegateKompensationVersand implements JavaDelegate {

    private final Logger log = LoggerFactory.getLogger(DelegateKompensationVersand.class);
    @Autowired
    private BestellungService bestellungService;

    @Autowired
    private MessageSender messageSender;


    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String bestellnummer = execution.getBusinessKey();
        Bestellung bestellung = bestellungService.getBestellungByBestellnummer(bestellnummer);
        if(bestellung.getLieferungstatus() != Status.STORNIERT) {
            messageSender.sendBestellung("versand-stornieren", bestellung);
        } else {
            log.info("Kompensation Versand nicht notwendig da bereits storniert. Bestellung: " + bestellnummer);
        }

    }
}
