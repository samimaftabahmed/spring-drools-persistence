package com.sam.springdroolspersistence.repository;

import org.drools.persistence.info.SessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroolsSessionRepository extends JpaRepository<SessionInfo, Long> {
}
