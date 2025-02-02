package dev.sentomero.amsIdea.controller;

import dev.sentomero.amsIdea.dto.AmsUserDto;
import dev.sentomero.amsIdea.model.AmsUser;
import dev.sentomero.amsIdea.service.AmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/api/users")  // Added /api prefix for better organization
public class AmsUserController {

    private final AmsUserService amsUserService;

    @Autowired
    public AmsUserController(AmsUserService amsUserService) {
        this.amsUserService = amsUserService;
    }

    // POST - Create new user
    @PostMapping("/register")
    public ResponseEntity<AmsUserDto> createUser(@RequestBody AmsUserDto amsUserDto) {
        AmsUserDto savedUser = amsUserService.savedUser(amsUserDto);
        return ResponseEntity.status(201).body(savedUser);
    }

    // POST - Login user
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AmsUserDto amsUserDto) {
        boolean isAuthenticated = amsUserService.authenticateUser(amsUserDto.getAmsUsername(), amsUserDto.getAmsPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }
    }

    // PUT - Update user
    @PutMapping("/{id}")
    public ResponseEntity<AmsUserDto> updateUser(@PathVariable("id") int id, @RequestBody AmsUserDto amsUserDto) {
        AmsUserDto updatedUser = amsUserService.updateUser(id, amsUserDto);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE - Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        amsUserService.deleteUser(id);
        return ResponseEntity.ok("User with ID " + id + " has been deleted successfully.");
    }

    // GET - Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<AmsUserDto> getUserById(@PathVariable("id") int id) {
        AmsUserDto user = amsUserService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // GET - Get all users
    @GetMapping
    public ResponseEntity<List<AmsUserDto>> getAllUsers() {
        List<AmsUserDto> users = amsUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}