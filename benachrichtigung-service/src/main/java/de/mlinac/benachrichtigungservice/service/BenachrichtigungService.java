package de.mlinac.benachrichtigungservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BenachrichtigungService {

    private void benachrichtigungBestellung(String bestellnummer, String email){
        // Bestellung eingegangen
    }
    private void benachrichtigungZahlung(String bestellnummer, String email){
        //Zahlung eingenagen
    }

    private void benachrichtigungVersand(String bestellnummer, String email){
        // Bestellung verschickt
    }


    private RestTemplate restTemplate = new RestTemplate();

}
