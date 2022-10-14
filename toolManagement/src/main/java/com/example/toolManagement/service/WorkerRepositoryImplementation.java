package com.example.toolManagement.service;

import com.example.toolManagement.entities.User;
import com.example.toolManagement.entities.Worker;
import com.example.toolManagement.model.JwtRequest;
import com.example.toolManagement.repository.WorkeRepository;
import com.example.toolManagement.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerRepositoryImplementation {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    WorkeRepository workeRepository;

    public List<Worker> getWorkers() {
        return workerRepository.findAll();
    }

    public Worker createWorker(Worker worker) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername(worker.getWorkerUsername());
        user.setRole("WORKER");
        String password=passwordEncoder.encode(worker.getWorkerPassword());
        worker.setWorkerPassword(password);
        user.setPassword(password);
        workeRepository.save(user);
        return workerRepository.save(worker);
    }

    public void removeWorker(Long workerId) {
        workerRepository.deleteById(workerId);
    }

    public Worker updateTool(Worker worker) {
        Worker existingWorker = workerRepository.findById(worker.getWorkerId()).orElse(null);
        if(existingWorker != null) {
            existingWorker.setWorkerName(worker.getWorkerName());
            existingWorker.setWorkerPassword(worker.getWorkerPassword());
            existingWorker.setWorkerUsername(worker.getWorkerUsername());
            existingWorker.setWorkerSalary(worker.getWorkerSalary());
            return workerRepository.save(existingWorker);
        }
        return null;
    }
    public Worker getWorker(Long workerId) {
        return workerRepository.findById(workerId).orElse(null);
    }
}