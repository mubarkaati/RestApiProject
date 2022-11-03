package com.springrest.LDManagement.config;

import com.springrest.LDManagement.entities.User;
import com.springrest.LDManagement.model.UserDetails;
import com.springrest.LDManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        System.out.println(user.getEmail() + "   " + user.getPassword() + "  " + user.getRole());
        if (user == null)
            throw new UsernameNotFoundException("User Not Found");
        return new UserDetails(user);
    }
}