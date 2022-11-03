package com.springrest.LDManagement.service;

import com.springrest.LDManagement.entities.User;
import com.springrest.LDManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRepositoryImplementation {

    @Autowired
    UserRepository userRepository;

    public List<String> getEmails() {
        List<User> users = userRepository.findAll();
        List<String> emails = new ArrayList<>();
        int count = 0;
        if (users.size()>0) {
            users.stream().forEach(user -> emails.add(user.getEmail()));
        }
        return emails;
    }
}