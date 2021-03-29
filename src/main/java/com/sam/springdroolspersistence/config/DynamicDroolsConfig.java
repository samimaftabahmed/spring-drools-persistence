package com.sam.springdroolspersistence.config;

import com.sam.springdroolspersistence.service.PersistentSessionDAO;
import org.drools.persistence.info.SessionInfo;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.Environment;
import org.kie.api.runtime.EnvironmentName;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Configuration
public class DynamicDroolsConfig {

    private KieServices kieServices;
    private KieFileSystem kieFileSystem;

    @Autowired
    private PersistentSessionDAO persistentSessionDAO;
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;


    @PostConstruct
    private void init() {
        this.kieServices = KieServices.Factory.get();
        this.kieFileSystem = kieServices.newKieFileSystem();
    }

    @Bean
    public KieServices getKieServices() {
        return this.kieServices;
    }

    @Bean
    public KieContainer getKieContainer() {
        kieFileSystem.write(ResourceFactory.newClassPathResource("rules/rules.drl"));
        final KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem).buildAll();
        KieModule kieModule = kb.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

    @Bean
    public KieFileSystem getFileSystem() {
        return kieFileSystem;
    }

    @Bean
    public KieSession kieSession() {
        List<SessionInfo> sessionDetails = persistentSessionDAO.getSessionDetails();

        if (sessionDetails.size() == 0) {
            return kieServices.getStoreServices().newKieSession(getKieContainer().getKieBase(), null, getEnv());
        } else {
            return kieServices.getStoreServices().loadKieSession(sessionDetails.get(0).getId(), getKieContainer().getKieBase(), null, getEnv());
        }
    }

    private Environment getEnv() {
        Environment env = kieServices.newEnvironment();
        env.set(EnvironmentName.ENTITY_MANAGER_FACTORY, entityManagerFactory);
        env.set(EnvironmentName.TRANSACTION_MANAGER, platformTransactionManager);
        env.set(EnvironmentName.USE_PESSIMISTIC_LOCKING, true);
        env.set(EnvironmentName.USE_PESSIMISTIC_LOCKING_MODE, LockModeType.PESSIMISTIC_FORCE_INCREMENT.name());
        return env;
    }
}

