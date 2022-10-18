package com.example.toolManagement.controller;

import com.example.toolManagement.model.JwtRequest;
import com.example.toolManagement.model.JwtResponse;
import com.example.toolManagement.repository.UserRepository;
import com.example.toolManagement.repository.WorkerRepository;
import com.example.toolManagement.utils.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
    WorkerRepository workerRepository;

    @PostMapping("/authenticate")
    public JwtResponse authenicate(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("INVALID_CREDENTIAL", badCredentialsException);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtility.generateToken(userDetails);
        final String userRole = userRepository.findByUsername(jwtRequest.getUsername()).getRole();
        final Long userId = workerRepository.findIdByUsername(jwtRequest.getUsername());
        if (userRole.equals("WORKER")) {
            return new JwtResponse(token, userRole, userId);
        } else {
            return new JwtResponse(token, userRole, 1L);
        }
    }
}