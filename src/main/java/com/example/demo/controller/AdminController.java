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

  @GetMapping("/login")
    public boolean login(@RequestParam String username,
                     @RequestParam String password) {
    return adminService.login(username, password);
}

    @PostMapping("/register")
    public Admin register(@RequestBody Admin admin) {
        return adminService.save(admin);
    }
}
