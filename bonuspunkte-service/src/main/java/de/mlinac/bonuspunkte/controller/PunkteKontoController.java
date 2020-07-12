package de.mlinac.bonuspunkte.controller;


import de.mlinac.bonuspunkte.model.PunkteKonto;
import de.mlinac.bonuspunkte.service.PunkteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PunkteKontoController {

    @Autowired
    private PunkteService punkteService;

    /*
    @RequestMapping(value="/addPunkte/{kundeId}/{neuePunkte}", method = RequestMethod.PUT)
    public PunkteKonto addPunkte(@PathVariable("kundeId") int kundeId, @PathVariable("neuePunkte") int neuePunkte){
        return punkteService.addPunkte(kundeId, neuePunkte);
    }

     */

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public List<PunkteKonto> getPunkteKonten(){
        return punkteService.getPunkteKonten();
    }


}
