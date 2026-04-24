package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.service.ProductService;
import com.example.demo.service.AdminService;
import com.example.demo.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    private final ProductService productService;
    private final AdminService adminService;

    public ProductController(ProductService productService,
                             AdminService adminService) {
        this.productService = productService;
        this.adminService = adminService;
    }

    // ✅ Public - Get all products
    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    // ✅ Public - Get by category
    @GetMapping("/category/{id}")
    public List<Product> getByCategory(@PathVariable Long id) {
        return productService.getByCategory(id);
    }

    // 🔒 Admin - Create product
    @PostMapping
    public Product create(
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam Boolean inStock,
            @RequestParam Long categoryId,
            @RequestParam("image") MultipartFile image,
            @RequestHeader("admin-username") String username,
            @RequestHeader("admin-password") String password
    ) throws Exception {

        if (!adminService.login(username, password)) {
            throw new RuntimeException("Unauthorized - Admin only");
        }

        // Save image
        String uploadDir = System.getProperty("user.dir") + "/uploads/";
        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);

        Files.createDirectories(filePath.getParent());
        Files.write(filePath, image.getBytes());
        System.out.println("NAME = " + name);
        System.out.println("PRICE = " + price);
        System.out.println("CATEGORY ID = " + categoryId);
        System.out.println("IN STOCK = " + inStock);
        System.out.println("IMAGE = " + image.getOriginalFilename());

        return productService.save(
                name,
                price,
                inStock,
                categoryId,
                fileName
        );

    }

    // 🔒 Admin - Delete product
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id,
                       @RequestHeader("admin-username") String username,
                       @RequestHeader("admin-password") String password) {

        if (!adminService.login(username, password)) {
            throw new RuntimeException("Unauthorized - Admin only");
        }

        productService.delete(id);
    }

}
