package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.repository.AdminRepository;
import com.example.demo.entity.Admin;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean login(String username, String password) {
        return adminRepository.findByUsername(username)
                .map(admin -> admin.getPassword().equals(password))
                .orElse(false);
    }



    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }
}