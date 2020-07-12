package de.mlinac.checkoutservice.controller;

import de.mlinac.checkoutservice.domain.*;
import de.mlinac.checkoutservice.service.CheckoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@RestController
public class CheckoutServiceController {

    private final Logger log = LoggerFactory.getLogger(CheckoutServiceController.class);

    @Autowired
    private CheckoutService checkoutService;

    @RequestMapping(value="/test", method = RequestMethod.PUT)
    public Session test1() {
        Kunde kunde = new Kunde(new Random().nextInt() & Integer.MAX_VALUE, "Marko", "Mlinac", "marko.ml@gmx.de");
        Adresse rechnungsaddresse = new Adresse("Rechnungsstraße 2", "10000", "Hamburg");
        Adresse lieferaddresse = new Adresse("Rechnungsstraße 2", "10000", "Hamburg");
        Artikel artikel1 = new Artikel(123, "Hose blau", 10.00);
        checkoutService.saveArtikel(artikel1);
        Artikel artikel2 = new Artikel(124, "Hose rot", 10.00);
        checkoutService.saveArtikel(artikel2);
        Artikelwahl artikelwahl = new Artikelwahl(artikel1, 2);
        Artikelwahl artikelwah2 = new Artikelwahl(artikel1, 4);
        List<Artikelwahl> artikelwahlList = new ArrayList<>();
        artikelwahlList.add(artikelwahl);
        checkoutService.saveArtikelWahl(artikelwahl);
        artikelwahlList.add(artikelwah2);
        checkoutService.saveArtikelWahl(artikelwah2);
        Session session = new Session(kunde, rechnungsaddresse, lieferaddresse, "paypal", 100, artikelwahlList);
        checkoutService.test1(session);
        return session;
    }

    @RequestMapping(value="/getSessions", method = RequestMethod.GET)
    public List<Session> getTest(){
        return checkoutService.getListSession();
    }

    @RequestMapping(value="/createAndShow", method = RequestMethod.GET)
    public List<Session> createAndShow(){
        test1();
        return getTest();
    }

    /*
    @RequestMapping(value="/testCheckout", method = RequestMethod.GET)
    public String testCheckout(){
        checkoutService.publishCheckout();
    }

     */
    @RequestMapping(value="/session/{id}", method = RequestMethod.GET)
    public Session sessionById(@PathVariable ("id") int id){
        return checkoutService.findSessionById(id);
    }

    @RequestMapping(value="/checkout/{id}", method = RequestMethod.GET)
    public String checkoutSession(@PathVariable ("id") int id){
        Session session = checkoutService.findSessionById(id);
        checkoutService.publishCheckout1(session);
        return "Session " + session.getId() + "published.";
    }
    @RequestMapping(value="/checkout1/randomSession", method = RequestMethod.GET)
    public  Session checkoutRandomSession(){
        Session session = test1();
        checkoutService.publishCheckout1(session);
        return checkoutService.findSessionById(session.getId());
    }

    @RequestMapping(value="/checkout1/randomSession/{y}", method = RequestMethod.GET)
    public String checkoutRandomSessions1(@PathVariable("y") int y){
        for(int i = 0 ; i <= y; i++) {
/*
           try{
                TimeUnit.MILLISECONDS.sleep(300);
               Session session = test1();
               checkoutService.publishCheckout1(session);}
            catch(InterruptedException ex){
                log.info(ex.toString());
            }

 */
            Session session = test1();
            checkoutService.publishCheckout1(session);
        }
        return y + " CheckOuts. ";
    }

    @RequestMapping(value="/checkout2/randomSession", method = RequestMethod.GET)
    public  Session checkoutRandomSession2(){
        Session session = test1();
        checkoutService.publishCheckout2(session);
        return checkoutService.findSessionById(session.getId());
    }

    @RequestMapping(value="/checkout2/randomSession/{y}", method = RequestMethod.GET)
    public String checkoutRandomSessions2(@PathVariable("y") int y){
        for(int i = 0 ; i <= y; i++) {

            try{
                TimeUnit.MILLISECONDS.sleep(300);
                Session session = test1();
                checkoutService.publishCheckout2(session);}
            catch(InterruptedException ex){
                log.info(ex.toString());
            }
        }
        return y + " CheckOuts. ";
    }

    @RequestMapping(value="/checkout3/randomSession", method = RequestMethod.GET)
    public  Session checkoutRandomSession3(){
        Session session = test1();
        checkoutService.publishCheckout3(session);
        return checkoutService.findSessionById(session.getId());
    }


}
