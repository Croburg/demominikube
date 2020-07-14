package de.mlinac.checkoutservice.service;


import de.mlinac.checkoutservice.domain.Artikel;
import de.mlinac.checkoutservice.domain.Artikelwahl;
import de.mlinac.checkoutservice.domain.Session;
import de.mlinac.checkoutservice.repository.ArtikelRepository;
import de.mlinac.checkoutservice.repository.ArtikelwahlRepository;
import de.mlinac.checkoutservice.repository.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CheckoutService {
    private final Logger log = LoggerFactory.getLogger(CheckoutService.class);

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private ArtikelRepository artikelRepository;
    @Autowired
    private ArtikelwahlRepository artikelwahlRepository;
    @Autowired
    private KafkaTemplate<String, Session> kafkaTemplateSession;

    public void test1(Session session){

        sessionRepository.save(session);
        log.info("Session ID: " +session.getId() + " gespeichert.");
    }

    public void saveArtikel(Artikel artikel){
        artikelRepository.save(artikel);
    }

    public void saveArtikelWahl(Artikelwahl artikelwahl){
        artikelwahlRepository.save(artikelwahl);
    }

    public List<Session> getListSession(){
        List<Session> sessionList = sessionRepository.findAll();

        return sessionList;
    }

    public String publishCheckout1(Session session){
        kafkaTemplateSession.send("checkout1", session);
        log.info("Session ID: "+ session.getId() + " published. TOPIC: 'checkout1'");
        return "Session sent.";
    }

    public String publishCheckout2(Session session){
        kafkaTemplateSession.send("checkout2", session);
        log.info("Session ID: "+ session.getId() + " published. TOPIC: 'checkout2'");
        return "Session sent.";
    }
    public String publishCheckout3(Session session){
        kafkaTemplateSession.send("checkout3", session);
        log.info("Session ID: "+ session.getId() + " published. TOPIC: 'checkout3'");
        return "Session sent.";
    }

    public Session findSessionById(int id){
       return sessionRepository.findById(id);
    }
}
