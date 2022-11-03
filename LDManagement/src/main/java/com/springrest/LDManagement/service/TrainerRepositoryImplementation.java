package com.springrest.LDManagement.service;

import com.springrest.LDManagement.entities.Domain;
import com.springrest.LDManagement.entities.Trainer;
import com.springrest.LDManagement.entities.User;
import com.springrest.LDManagement.model.DTO.TrainerDto;
import com.springrest.LDManagement.repository.DomainRepository;
import com.springrest.LDManagement.repository.EmploymentTypeRepository;
import com.springrest.LDManagement.repository.TrainerRepository;
import com.springrest.LDManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerRepositoryImplementation {

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    DomainRepository domainRepository;

    @Autowired
    EmploymentTypeRepository employmentTypeRepository;

    @Autowired
    UserRepository userRepository;

    public Trainer addTrainer(TrainerDto trainerDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(trainerDto.getTrainerPassword());
        User user = new User();
        user.setPassword(encryptedPassword);
        user.setEmail(trainerDto.getTrainerEmail());
        user.setRole("TRAINER");
        userRepository.save(user);

        Domain domain = domainRepository.findById(trainerDto.getDomainId()).orElse(null);
        domain.setAssigned(true);
        domainRepository.save(domain);

        Trainer trainer = new Trainer();
        trainer.setTrainerEmail(trainerDto.getTrainerEmail());
        trainer.setTrainerName(trainerDto.getTrainerName());
        trainer.setTrainerSalary(trainerDto.getTrainerSalary());
        trainer.setDomain(domain);
        trainer.setEmploymentType(employmentTypeRepository.findById(trainerDto.getEmploymentTypeId()).orElse(null));
        System.out.println(trainer);
        return trainerRepository.save(trainer);
    }

    public List<Trainer> getTrainers() {
        List<Trainer> trainers = trainerRepository.findAll();
        trainers.stream().forEach(trainer -> System.out.println(trainer.getTrainerName()));
        return trainers.stream().filter(trainer -> !trainer.isDeleted()).collect(Collectors.toList());
    }

    public Trainer deleteTrainer(Long trainerId) {
        Trainer existingTrainer = trainerRepository.findById(trainerId).orElse(null);
        if (existingTrainer != null) {
            existingTrainer.setDeleted(true);
            User user = userRepository.findByEmail(existingTrainer.getTrainerEmail());
            userRepository.deleteById(user.getId());
            Domain domain = existingTrainer.getDomain();
            existingTrainer.setDomain(null);
            domain.setAssigned(false);
            domainRepository.save(domain);
            return trainerRepository.save(existingTrainer);
        }
        else {
            return null;
        }
    }

    public Trainer updateTrainer(TrainerDto trainerDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(trainerDto.getTrainerPassword());
        Trainer existingTrainer = trainerRepository.findById(trainerDto.getTrainerId()).orElse(null);
        User existingUser = userRepository.findByEmail(existingTrainer.getTrainerEmail());
        existingUser.setPassword(encryptedPassword);
        existingUser.setEmail(trainerDto.getTrainerEmail());
        existingUser.setRole("TRAINER");
        userRepository.save(existingUser);

        Domain domain = existingTrainer.getDomain();
        domain.setAssigned(false);
        domainRepository.save(domain);

        Domain domain1 = domainRepository.findById(trainerDto.getDomainId()).orElse(null);
        domain1.setAssigned(true);
        domainRepository.save(domain1);

        Trainer exitingTrainer = trainerRepository.findById(trainerDto.getTrainerId()).orElse(null);
        exitingTrainer.setTrainerEmail(trainerDto.getTrainerEmail());
        exitingTrainer.setTrainerName(trainerDto.getTrainerName());
        exitingTrainer.setTrainerSalary(trainerDto.getTrainerSalary());
        exitingTrainer.setDomain(domain);
        exitingTrainer.setEmploymentType(employmentTypeRepository.findById(trainerDto.getEmploymentTypeId()).orElse(null));
        System.out.println(exitingTrainer);
        return trainerRepository.save(exitingTrainer);
    }

    public Trainer getTrainer(Long trainerId) {
        return trainerRepository.findById(trainerId).orElse(null);
    }
}