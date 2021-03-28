package com.sam.springdroolspersistence.service;

import com.sam.springdroolspersistence.repository.DroolsSessionRepository;
import org.drools.persistence.info.SessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistentSessionDAO {

    @Autowired
    private DroolsSessionRepository droolsSessionRepository;

    public List<SessionInfo> getSessionDetails() {
        return droolsSessionRepository.findAll();
    }
}
