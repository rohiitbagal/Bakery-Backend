package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.service.AdminService;
import com.example.demo.entity.Admin;   // 🔥 ADD THIS

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

  @PostMapping("/login")
    public boolean login(@RequestBody Admin admin) {
        return adminService.login(admin.getUsername(), admin.getPassword());
}

    @PostMapping("/register")
    public Admin register(@RequestBody Admin admin) {
        return adminService.save(admin);
    }
}
