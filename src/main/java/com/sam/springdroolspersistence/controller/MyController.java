package com.sam.springdroolspersistence.controller;

import com.sam.springdroolspersistence.model.Person;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private KieSession kieSession;

    @Transactional
    @GetMapping("fire-person")
    public void firePerson() {
        init();
    }

    @EventListener(classes = ApplicationReadyEvent.class)
    public void init() {
        Person person = new Person();
        person.setName("Christy");
        kieSession.insert(person);
        kieSession.fireAllRules();
    }
}
