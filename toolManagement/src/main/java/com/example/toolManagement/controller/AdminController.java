package com.example.toolManagement.controller;

import com.example.toolManagement.entities.Tool;
import com.example.toolManagement.entities.Worker;
import com.example.toolManagement.service.OrderRepositoryImplementation;
import com.example.toolManagement.service.ToolRepositoryImplementation;
import com.example.toolManagement.service.WorkerRepositoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    ToolRepositoryImplementation toolRepositoryImplementation;

    //Tools Operations Mapping Starts Here
    @PostMapping("/createTool")
    public ResponseEntity createTool(@RequestBody Tool tool) {
        try {
            return new ResponseEntity(Optional.of(toolRepositoryImplementation.createTool(tool)), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTools")
    public ResponseEntity getTools() {
        try {
            List<Tool> tools = toolRepositoryImplementation.getTools();
            if (tools.size() > 0) {
                return new ResponseEntity(tools, HttpStatus.OK);
            } else {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            }
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateTool")
    public ResponseEntity updateTool(@RequestBody Tool tool) {
        try {
            return new ResponseEntity(Optional.of(toolRepositoryImplementation.updateTool(tool)), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteTool/{toolId}")
    public ResponseEntity deleteTool(@PathVariable String toolId) {
        try {
            toolRepositoryImplementation.deleteTool(Long.parseLong(toolId));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Tools Operations Mapping Ends Here

    @Autowired
    WorkerRepositoryImplementation workerRepositoryImplementation;

    //Worker Operations Mapping Starts Here
    @PostMapping("/createWorker")
    public ResponseEntity createWorker(@RequestBody Worker worker) {
        try {
            return new ResponseEntity(Optional.of(workerRepositoryImplementation.createWorker(worker)), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getWorkers")
    public ResponseEntity getWorkers() {
        try {
            List<Worker> workers = workerRepositoryImplementation.getWorkers();
            if (workers.size() > 0) {
                return new ResponseEntity(workers, HttpStatus.OK);
            } else {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            }
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getWorker/{workerId}")
    public ResponseEntity getWorker(@PathVariable String workerId) {
        try {
            Worker worker = workerRepositoryImplementation.getWorker(Long.parseLong(workerId));
            if (worker != null) {
                return new ResponseEntity(worker, HttpStatus.OK);
            } else {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            }
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateWorker")
    public ResponseEntity updateWorker(@RequestBody Worker worker) {
        try {
            return new ResponseEntity(Optional.of(workerRepositoryImplementation.updateWorker(worker)), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteWorker/{workerId}")
    public ResponseEntity deleteWorker(@PathVariable String workerId) {
        try {
            workerRepositoryImplementation.removeWorker(Long.parseLong(workerId));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Worker Operations Mapping Ends Here

    @Autowired
    OrderRepositoryImplementation orderRepositoryImplementation;

    @GetMapping("/getOrders")
    public ResponseEntity getOrders() {
        try {
            List<Worker> orders = workerRepositoryImplementation.getWorkers();
            if (orders.size() > 0) {
                return new ResponseEntity(orders, HttpStatus.OK);
            } else {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            }
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}