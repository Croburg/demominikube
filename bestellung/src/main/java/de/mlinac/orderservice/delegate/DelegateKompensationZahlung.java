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
public class DelegateKompensationZahlung implements JavaDelegate {

    private final Logger log = LoggerFactory.getLogger(DelegateKompensationZahlung.class);
    @Autowired
    private BestellungService bestellungService;

    @Autowired
    private MessageSender messageSender;

    private String bestellnummer;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        bestellnummer = execution.getBusinessKey();
        Bestellung bestellung = bestellungService.getBestellungByBestellnummer(bestellnummer);
        if(bestellung.getZahlungstatus() != Status.STORNIERT){
            log.info("SAGA ZAHLUNG" + bestellnummer);
        messageSender.sendBestellung("zahlung-gutschreiben", bestellung);}

        else{
            log.info("Kompensation Zahlung nicht notwendig da bereits storniert. Bestellung: " + bestellnummer);
        }
    }
}
