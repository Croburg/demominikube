package de.mlinac.bonuspunkte.service;

import de.mlinac.bonuspunkte.message.MessageSender;
import de.mlinac.bonuspunkte.model.Bestellung;
import de.mlinac.bonuspunkte.model.PunkteKonto;
import de.mlinac.bonuspunkte.repository.PunkteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class PunkteService {

    private final Logger log = LoggerFactory.getLogger(PunkteService.class);

    @Autowired
    private PunkteRepository punkteRepository;

    @Autowired
    private MessageSender messageSender;

    public PunkteKonto addPunkte(Bestellung bestellung){
        PunkteKonto punkteKonto = new PunkteKonto();
        if(punkteRepository.existsById(bestellung.getKunde().getKundeId())){
            punkteKonto = punkteRepository.findByKundeId(bestellung.getKunde().getKundeId());
            punkteKonto.setBonuspunkte(punkteKonto.getBonuspunkte() + bestellung.getBonuspunkte());
        } else {
             punkteKonto.setKundeId(bestellung.getKunde().getKundeId());
             punkteKonto.setBonuspunkte(bestellung.getBonuspunkte());
        }
        punkteRepository.save(punkteKonto);
        punkteKonto.setBestellnummer(bestellung.getBestellnummer());
        log.info("Punkte gutgeschrieben für Bestellung: "+ punkteKonto.getBestellnummer());
        messageSender.sendStatus("bonuspunkte-gutgeschrieben", punkteKonto.getBestellnummer());
        return punkteKonto;
    }


    public PunkteKonto abziehenPunkte(Bestellung bestellung){
       // List<PunkteKonto> punkteKontoList = punkteRepository.findAll();
        PunkteKonto punkteKonto = punkteRepository.findByKundeId(bestellung.getKunde().getKundeId());
        punkteKonto.setBestellnummer(bestellung.getBestellnummer());
       /* for(PunkteKonto punkteKonto1 : punkteKontoList){
            if(punkteKonto1.getKunde().getKundeId() == bestellung.getKunde().getKundeId()){
                punkteKonto = punkteKonto1;
            }
        }

        */
        if(punkteKonto.getBonuspunkte() < bestellung.getBonuspunkte()){
            punkteKonto.setBonuspunkte(0);
            punkteRepository.save(punkteKonto);
            log.info("Punkte abgezogen für Bestellung: "+ punkteKonto.getBestellnummer());
            messageSender.sendStatus("bonuspunkte-abgezogen", punkteKonto.getBestellnummer());

            return punkteKonto;
        } else{
            punkteKonto.setBonuspunkte(punkteKonto.getBonuspunkte() - bestellung.getBonuspunkte());
            punkteRepository.save(punkteKonto);
            log.info("Punkte abgezogen für Bestellung: "+ punkteKonto.getBestellnummer());
            messageSender.sendStatus("bonuspunkte-abgezogen", punkteKonto.getBestellnummer());
            return punkteKonto;
        }
    }

    public List<PunkteKonto> getPunkteKonten(){
        List<PunkteKonto> punkteKontoList = punkteRepository.findAll();
        return punkteKontoList;
    }
}
