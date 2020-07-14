package de.mlinac.zahlungservice.service;

import de.mlinac.zahlungservice.domain.Status;
import de.mlinac.zahlungservice.domain.Zahlung;
import de.mlinac.zahlungservice.message.MessageSender;
import de.mlinac.zahlungservice.repository.ZahlungRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ZahlungService {

    private final Logger log = LoggerFactory.getLogger(ZahlungService.class);

    @Autowired
    private ZahlungRepository zahlungRepository;

    @Autowired
    private MessageSender messageSender;


    public void initialisiereZahlung(Zahlung zahlung){
        zahlung.setStatus(Status.OFFEN);
        zahlungRepository.save(zahlung);
        log.info("Zahlung initialisiert für Bestellung:" + zahlung.getBestellnummer() + "Status: " + zahlung.getStatus());
    }

    public void bezahlen(String bestellnummer){
        Zahlung zahlung = zahlungRepository.findByBestellnummer(bestellnummer);
        if(zahlung.getStatus() == Status.OFFEN) {
            zahlung.setStatus(Status.ABGESCHLOSSEN);
            zahlungRepository.save(zahlung);
            log.info("Zahlung abgeschlossen für Bestellung:" + zahlung.getBestellnummer() + "Status: " + zahlung.getStatus());
            messageSender.sendStatus("zahlung-erhalten", zahlung.getBestellnummer());
        } else {
            log.info("Zahlung für Bestellung: " +  bestellnummer +" bereits bezahlt oder storniert");
        }
    }

    public void gutschreiben(String bestellnummer){
        Zahlung zahlung = zahlungRepository.findByBestellnummer(bestellnummer);
        //do something
        log.info("Zahlung gutgeschrieben für Bestellung:" + zahlung.getBestellnummer() + "Status: " + zahlung.getStatus());
        messageSender.sendStatus("zahlung-gutgeschrieben", zahlung.getBestellnummer());
    }

    public void abbrechen(String bestellnummmer){
        Zahlung zahlung = zahlungRepository.findByBestellnummer(bestellnummmer);
        zahlung.setStatus(Status.STORNIERT);
        zahlungRepository.save(zahlung);
        log.info("Zahlung abgebrochen für Bestellung:" + zahlung.getBestellnummer() + "Status: " + zahlung.getStatus());
        messageSender.sendStatus("zahlung-fehlgeschlagen", zahlung.getBestellnummer());
    }

    public List<Zahlung> getAlleMitStatus(Status status){
        return zahlungRepository.findByStatus(status);
    }
}
