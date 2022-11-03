package com.springrest.LDManagement.service;

import com.springrest.LDManagement.entities.Domain;
import com.springrest.LDManagement.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DomainRepositoryImplementation {

    @Autowired
    DomainRepository domainRepository;

    public List<Domain> getDomains() {
        List<Domain> domains = domainRepository.findAll();
        if (domains.size() > 0) {
            return domains.stream().filter(domain -> !domain.isAssigned()).collect(Collectors.toList());
        } else {
            return null;
        }
    }


    public List<Domain> getAssignedDomains() {
        List<Domain> domains = domainRepository.findAll();
        if (domains.size() > 0) {
            return domains.stream().filter(domain -> domain.isAssigned()).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public List<Domain> getAllDomains() {
        List<Domain> domains = domainRepository.findAll();
        if (domains.size() > 0) {
            return domains;
        } else {
            return null;
        }
    }

}