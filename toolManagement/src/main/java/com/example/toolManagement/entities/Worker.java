package com.example.toolManagement.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "worker_details")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "worker_id", updatable = false)
    private Long workerId;

    @Column(name = "worker_name")
    private String workerName;

    @Column(name = "worker_username")
    private String workerUsername;

    @Column(name = "worker_password")
    private String workerPassword;

    @Column(name = "worker_salary")
    private float workerSalary;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "worker")
    private Set<Order> worker_orders;

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerUsername() {
        return workerUsername;
    }

    public void setWorkerUsername(String workerUsername) {
        this.workerUsername = workerUsername;
    }

    public String getWorkerPassword() {
        return workerPassword;
    }

    public void setWorkerPassword(String workerPassword) {
        this.workerPassword = workerPassword;
    }

    public float getWorkerSalary() {
        return workerSalary;
    }

    public void setWorkerSalary(float workerSalary) {
        this.workerSalary = workerSalary;
    }

    public Set<Order> getWorker_orders() {
        return worker_orders;
    }

    public void setWorker_orders(Set<Order> worker_orders) {
        this.worker_orders = worker_orders;
    }
}