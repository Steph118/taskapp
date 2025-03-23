package com.steph18.demo.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.steph18.demo.service.PersonService;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;
}
