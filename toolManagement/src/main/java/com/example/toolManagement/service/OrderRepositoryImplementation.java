package com.example.toolManagement.service;

import com.example.toolManagement.entities.Order;
import com.example.toolManagement.entities.Tool;
import com.example.toolManagement.entities.Worker;
import com.example.toolManagement.model.DummyOrder;
import com.example.toolManagement.model.DummyTool;
import com.example.toolManagement.repository.OrderRepository;
import com.example.toolManagement.repository.ToolRepository;
import com.example.toolManagement.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderRepositoryImplementation {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    ToolRepository toolRepository;

    public Order createOrder(DummyOrder dummyOrder) {
        List<DummyTool> dummyTools = dummyOrder.getRequiredTools();
        int count = 0;
        float totalPrice = 0;
        List<Tool> tools = new ArrayList<>();

        while (count < dummyTools.size()) {
            DummyTool dummyTool = dummyTools.get(count++);
            Tool tool = toolRepository.findById(dummyTool.getToolId()).orElse(null);
            tool.setToolQuantity(dummyTool.getToolQuantity());
            totalPrice = totalPrice + (tool.getToolPrice() * dummyTool.getToolQuantity());
            tools.add(tool);
        }

        Order order = new Order();
        order.setOrderStatus("Pending");
        order.setCustomerName(dummyOrder.getCustomerName());
        order.setWorker(workerRepository.findById(dummyOrder.getWorkerId()).orElse(null));
        order.setOrderTotalPrice(totalPrice);
        order.setOrderedTools(tools);
        return orderRepository.save(order);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrder(DummyOrder dummyOrder) {
        Order existingOrder = orderRepository.findById(dummyOrder.getOrderId()).orElse(null);
        List<DummyTool> dummyTools = dummyOrder.getRequiredTools();
        int count = 0;
        float totalPrice = 0;
        List<Tool> tools = new ArrayList<>();

        while (count < dummyTools.size()) {
            DummyTool dummyTool = dummyTools.get(count++);
            Tool tool = toolRepository.findById(dummyTool.getToolId()).orElse(null);
            tool.setToolQuantity(dummyTool.getToolQuantity());
            totalPrice = totalPrice + (tool.getToolPrice() * dummyTool.getToolQuantity());
            tools.add(tool);
        }

        existingOrder.setOrderStatus(dummyOrder.getOrderStatus());
        existingOrder.setCustomerName(dummyOrder.getCustomerName());
        //existingOrder.setWorker(workerRepository.findById(dummyOrder.getWorkerId()).orElse(null));
        existingOrder.setOrderTotalPrice(totalPrice);
        existingOrder.setOrderedTools(tools);
        return orderRepository.save(existingOrder);
    }

    public Set<Order> getOrdersByWorkerId(Long workerId) {
        Worker worker = workerRepository.findById(workerId).orElse(null);
        if (worker != null) {
            return worker.getWorker_orders();
        }
        else {
            return null;
        }
    }
}