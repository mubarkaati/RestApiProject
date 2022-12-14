package com.springrest.LDManagement.repository;

import com.springrest.LDManagement.entities.EmploymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentTypeRepository extends JpaRepository<EmploymentType, Long> {
}
