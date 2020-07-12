package de.mlinac.usertask.controller;

import de.mlinac.usertask.service.UsertaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsertaskController {

    @Autowired
    private UsertaskService usertaskService;


    @RequestMapping(value="/create/", method = RequestMethod.GET)
    public void createTask(){
        //usertaskService.createUserTask();
    }
}
