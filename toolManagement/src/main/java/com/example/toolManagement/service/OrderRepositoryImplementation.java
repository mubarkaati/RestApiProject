package com.example.toolManagement.service;

import com.example.toolManagement.entities.Order;
import com.example.toolManagement.entities.OrderTool;
import com.example.toolManagement.entities.Tool;
import com.example.toolManagement.entities.Worker;
import com.example.toolManagement.model.DummyOrder;
import com.example.toolManagement.model.DummyTool;
import com.example.toolManagement.repository.OrderRepository;
import com.example.toolManagement.repository.OrderToolRepository;
import com.example.toolManagement.repository.ToolRepository;
import com.example.toolManagement.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderRepositoryImplementation {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    ToolRepository toolRepository;

    @Autowired
    OrderToolRepository orderToolRepository;

    public Order createOrder(DummyOrder dummyOrder) {
        List<DummyTool> dummyTools = dummyOrder.getRequiredTools();
        int count = 0;
        float totalPrice = 0;
        List<Tool> tools = new ArrayList<>();
        System.out.println(dummyOrder);
        while (count < dummyTools.size()) {
            DummyTool dummyTool = dummyTools.get(count++);
            Tool tool = toolRepository.findById(dummyTool.getToolId()).orElse(null);
            totalPrice = totalPrice + (tool.getToolPrice() * dummyTool.getToolQuantity());
            tools.add(tool);
        }

        Order order = new Order();
        order.setOrderStatus("Pending");
        order.setCustomerName(dummyOrder.getCustomerName());
        order.setWorker(workerRepository.findById(dummyOrder.getWorkerId()).orElse(null));
        order.setOrderedTools(tools);
        order.setOrderTotalPrice(totalPrice);
        order = orderRepository.save(order);
        System.out.println(order);
        count = 0;
        while (count < dummyTools.size()) {
            DummyTool dummyTool = dummyTools.get(count++);
            Tool tool = toolRepository.findById(dummyTool.getToolId()).orElse(null);
            OrderTool orderTool = new OrderTool();
            orderTool.setOrderId(order.getOrderId());
            orderTool.setToolId(tool.getToolId());
            orderTool.setToolQuantity(dummyTool.getToolQuantity());
            System.out.println(orderTool);
            orderToolRepository.save(orderTool);
        }
        System.out.println(orderRepository.findById(order.getOrderId()).orElse(null));
        return orderRepository.findById(order.getOrderId()).orElse(null);
    }

    public List<Order> getOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders.size() > 0) {
            int count = 0;
            while (count < orders.size()) {
                Order order = orders.get(count++);
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
            return orders;
        }
        else {
            return null;
        }
    }

    public Order updateOrder(DummyOrder dummyOrder) {
        Order existingOrder = orderRepository.findById(dummyOrder.getOrderId()).orElse(null);
//        List<DummyTool> dummyTools = dummyOrder.getRequiredTools();
//        int count = 0;
//        float totalPrice = 0;
//        List<Tool> tools = new ArrayList<>();

//        while (count < dummyTools.size()) {
//            DummyTool dummyTool = dummyTools.get(count++);
//            Tool tool = toolRepository.findById(dummyTool.getToolId()).orElse(null);
//            tool.setToolQuantity(dummyTool.getToolQuantity());
//            totalPrice = totalPrice + (tool.getToolPrice() * dummyTool.getToolQuantity());
//            tools.add(tool);
//        }

        existingOrder.setOrderStatus(dummyOrder.getOrderStatus());
//        existingOrder.setCustomerName(dummyOrder.getCustomerName());
        //existingOrder.setWorker(workerRepository.findById(dummyOrder.getWorkerId()).orElse(null));
//        existingOrder.setOrderTotalPrice(totalPrice);
//        existingOrder.setOrderedTools(tools);
        return orderRepository.save(existingOrder);
    }

    public List<Order> getOrdersByWorkerId(Long workerId) {
        Worker worker = workerRepository.findById(workerId).orElse(null);
        if (worker != null) {
            List<Order> orders = worker.getWorker_orders();
            int count = 0;
            while (count < orders.size()) {
                Order order = orders.get(count++);
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
            return orders;
        }
        else {
            return null;
        }
    }
}