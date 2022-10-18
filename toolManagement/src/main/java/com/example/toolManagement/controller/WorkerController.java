package com.example.toolManagement.controller;

import com.example.toolManagement.entities.Order;
import com.example.toolManagement.model.DummyOrder;
import com.example.toolManagement.service.OrderRepositoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/worker")
@CrossOrigin
public class WorkerController {
    @Autowired
    OrderRepositoryImplementation orderRepositoryImplementation;

    @PostMapping("/createOrder")
    public ResponseEntity createOrder(@RequestBody DummyOrder dummyOrder) {
        try {
            return new ResponseEntity(Optional.of(orderRepositoryImplementation.createOrder(dummyOrder)), HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println(exception);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getWorkerOrders/{workerId}")
    public ResponseEntity getOrder(@PathVariable Long workerId) {
        try {
            List<Order> orders = orderRepositoryImplementation.getOrdersByWorkerId(workerId);
            if (orders.size() > 0) {
                return new ResponseEntity(orders, HttpStatus.OK);
            } else {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateOrder")
    public ResponseEntity updateOrder(@RequestBody DummyOrder dummyOrder) {
        try {
            return new ResponseEntity(Optional.of(orderRepositoryImplementation.updateOrder(dummyOrder)), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}