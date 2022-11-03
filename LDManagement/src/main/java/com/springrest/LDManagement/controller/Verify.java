package com.springrest.LDManagement.controller;

import com.springrest.LDManagement.model.JwtRequest;
import com.springrest.LDManagement.model.JwtResponse;
import com.springrest.LDManagement.repository.StudentRepository;
import com.springrest.LDManagement.repository.TrainerRepository;
import com.springrest.LDManagement.repository.UserRepository;
import com.springrest.LDManagement.utils.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class Verify {
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/authenticate")
    public JwtResponse authenicate(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getEmail(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("INVALID_CREDENTIAL", badCredentialsException);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        final String token = jwtUtility.generateToken(userDetails);
        final String userRole = userRepository.findByEmail(jwtRequest.getEmail()).getRole();
        if (userRole.equals("TRAINER")) {
            final Long trainerId = trainerRepository.findIdByTrainerEmail(jwtRequest.getEmail()).getTrainerId();
            return new JwtResponse(token, userRole, trainerId);
        } else if (userRole.equals("STUDENT")) {
            final Long studentId = studentRepository.findIdByStudentEmail(jwtRequest.getEmail()).getStudentId();
            return new JwtResponse(token, userRole, studentId);
        } else {
            return new JwtResponse(token, userRole, 1L);
        }
    }
}