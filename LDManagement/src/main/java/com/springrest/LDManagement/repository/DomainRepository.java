package com.springrest.LDManagement.repository;

import com.springrest.LDManagement.entities.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends JpaRepository<Domain, Long> {
}
