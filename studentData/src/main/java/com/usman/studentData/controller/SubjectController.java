package com.usman.studentData.controller;

import com.usman.studentData.model.dto.request.SubjectRequestDto;
import com.usman.studentData.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @PostMapping("/add")
    public ResponseEntity addSubject(@RequestBody SubjectRequestDto requestDto) {
        return new ResponseEntity(subjectService.addSubject(requestDto), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{subjectId}")
    public ResponseEntity updateSubject(@RequestBody SubjectRequestDto requestDto, @PathVariable int subjectId) {
        return new ResponseEntity(subjectService.updateSubject(requestDto, subjectId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{subjectId}")
    public ResponseEntity deleteSubject(@PathVariable int subjectId) {
        subjectService.deleteSubject(subjectId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllSubject() {
        return new ResponseEntity(subjectService.getAllSubject(), HttpStatus.OK);
    }
}