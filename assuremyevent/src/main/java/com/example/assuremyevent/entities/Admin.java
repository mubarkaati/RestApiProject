package com.example.assuremyevent.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String adminName;
    @Column(nullable = false)
    private String password;
}
