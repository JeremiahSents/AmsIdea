package dev.sentomero.amsIdea.controller;


import dev.sentomero.amsIdea.dto.AmsUserDto;
import dev.sentomero.amsIdea.model.AmsUser;
import dev.sentomero.amsIdea.service.AmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class AmsUserController {

    private  final AmsUserService amsUserService;

    @Autowired
    public AmsUserController(AmsUserService amsUserService) {
        this.amsUserService = amsUserService;
    }

    //POST - Create new user

    @PostMapping("/createUser") //http://localhost:8080/users/createUser
    public ResponseEntity<AmsUserDto> createUser(@RequestBody AmsUserDto amsUserDto) {
        AmsUserDto savedUser = amsUserService.savedUser(amsUserDto);
        return ResponseEntity.status(201).body(savedUser);
    }



    //PUT - Update user
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<AmsUserDto> updateUser(@PathVariable("id") int id, @RequestBody AmsUserDto amsUserDto) {
        AmsUserDto updatedUser = amsUserService.updateUser(id, amsUserDto);
        return ResponseEntity.ok(updatedUser);
    }


    //DELETE - Delete user
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        amsUserService.deleteUser(id);
        return ResponseEntity.ok("User with ID " + id + " has been deleted successfully.");
    }

    //GET - Get user by ID
    @GetMapping("/getUser/{id}")
    public ResponseEntity<AmsUserDto> getUserById(@PathVariable("id") int id) {
        AmsUserDto user = amsUserService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    //GET - Get all users
    @GetMapping("/AllAmsUsers")
    public ResponseEntity<List<AmsUserDto>> getAllUsers() {
        List<AmsUserDto> users = amsUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
