package com.springrest.LDManagement.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "admin_details")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long adminId;
    @Column(nullable = false)
    private String adminName;
    @Column(nullable = false, unique = true)
    private String adminEmail;

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
}
