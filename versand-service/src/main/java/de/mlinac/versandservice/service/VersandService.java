
package de.mlinac.versandservice.service;


import de.mlinac.versandservice.domain.*;
import de.mlinac.versandservice.message.MessageSender;
import de.mlinac.versandservice.repository.VersandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;


@Service
@Transactional
public class VersandService {

    private Logger log = LoggerFactory.getLogger(VersandService.class);

    @Autowired
    private VersandRepository versandRepository;

    @Autowired
    private MessageSender messageSender;


    public List<Lieferung> getAll(){
        return versandRepository.findAll();
    }


    /*
    public void test1(){
        Adresse adresse = new Adresse("Straße 2", "2356", "Hamburg");
        Artikel artikel1 = new Artikel(123, "Hose");
        Lieferposition position1 = new Lieferposition(artikel1, 3);
        Artikel artikel2 = new Artikel(124, "Hose");
        Lieferposition position2 = new Lieferposition(artikel2, 3);
        List<Lieferposition> positionListe = new ArrayList<>();
        positionListe.add(position1);
        positionListe.add(position2);
        Kunde kunde = new Kunde(1, "Marko", "Mlinac","marko.ml@gmx.de");
        Lieferung lieferung = new Lieferung("6789", "DHL", kunde, adresse, positionListe);
        versandRepository.save(lieferung);
    }

     */
    public void initialisiereLieferung(Lieferung lieferung){
        log.info("Lieferung initialisert für Bestellung: " + lieferung.getBestellnummer());
        lieferung.setStatus(Status.OFFEN);
        versandRepository.save(lieferung);

    }

    public void lieferungVerschickt(String bestellnummer){
        List<Lieferung> lieferungList = versandRepository.findByStatus(Status.OFFEN);
        for(Lieferung lieferung : lieferungList){
            log.info("Lieferung verschickt für Bestellung: " + lieferung.getBestellnummer());
            lieferung.setStatus(Status.ABGESCHLOSSEN);
            versandRepository.save(lieferung);
            messageSender.sendStatus("lieferung-verschickt", lieferung.getBestellnummer());
        }
    }

    public void lieferungAbbrechen(String bestellnummer){
        List<Lieferung> lieferungList = versandRepository.findByStatus(Status.OFFEN);
        for(Lieferung lieferung : lieferungList){
            if(lieferung.getBestellnummer() == bestellnummer) {
                lieferung.setStatus(Status.STORNIERT);
                versandRepository.save(lieferung);
                log.info("Lieferung storniert für Bestellung: " + lieferung.getBestellnummer());
                messageSender.sendStatus("lieferung-fehlgeschlagen", bestellnummer);
            }
        }
    }

    public void lieferungStorniern(String bestellnummer){
        List<Lieferung> lieferungList = versandRepository.findByStatus(Status.OFFEN);
        for(Lieferung lieferung : lieferungList){
            lieferung.setStatus(Status.STORNIERT);
            versandRepository.save(lieferung);
            log.info("Lieferung storniert für Bestellung: " + lieferung.getBestellnummer());
            messageSender.sendStatus("lieferung-storniert", bestellnummer);
        }
    }

    public List<Lieferung> getAlleMitStatus(Status status){
        return versandRepository.findByStatus(status);
    }
}
