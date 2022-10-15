package com.example.toolManagement.service;

import com.example.toolManagement.entities.Order;
import com.example.toolManagement.entities.Tool;
import com.example.toolManagement.entities.User;
import com.example.toolManagement.entities.Worker;
import com.example.toolManagement.model.JwtRequest;
import com.example.toolManagement.repository.OrderToolRepository;
import com.example.toolManagement.repository.WorkeRepository;
import com.example.toolManagement.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkerRepositoryImplementation {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    WorkeRepository workeRepository;
    @Autowired
    OrderToolRepository orderToolRepository;

    public List<Worker> getWorkers() {
        List<Worker> workers = workerRepository.findAll();

        int workerCount = 0;
        while (workerCount < workers.size()) {
            Worker worker = workers.get(workerCount++);
            List<Order> orders = worker.getWorker_orders();
            if (orders.size() > 0) {
                int orderCount = 0;
                while (orderCount < orders.size()) {
                    Order order = orders.get(orderCount++);
                    List<Tool> tools = order.getOrderedTools();
                    int innerCount = 0;
                    Map<Long, Integer> toolsQuantity = new HashMap<>();
                    while (innerCount < tools.size()) {
                        Tool tool = tools.get(innerCount++);
                        int toolQuantity = orderToolRepository.findToolquantity(order.getOrderId(), tool.getToolId());
                        toolsQuantity.put(tool.getToolId(), toolQuantity);
                    }
                    order.setToolsQuantity(toolsQuantity);
                }
            }
        }
        return workers;
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
        String username = workerRepository.findById(workerId).orElse(null).getWorkerUsername();
        workeRepository.deleteById(workeRepository.findByUsername(username).getId());
        workerRepository.deleteById(workerId);
    }

    public Worker updateWorker(Worker worker) {
        Worker existingWorker = workerRepository.findById(worker.getWorkerId()).orElse(null);
        System.out.println(existingWorker);
        if(existingWorker != null) {
            String username = workerRepository.findById(existingWorker.getWorkerId()).orElse(null).getWorkerUsername();
            User user = workeRepository.findByUsername(username);
            user.setUsername(worker.getWorkerUsername());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password=passwordEncoder.encode(worker.getWorkerPassword());
            existingWorker.setWorkerPassword(password);
            user.setPassword(password);
            workeRepository.save(user);
            existingWorker.setWorkerName(worker.getWorkerName());
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