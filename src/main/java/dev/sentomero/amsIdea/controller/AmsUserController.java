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
    public ResponseEntity<AmsUser> createUser(@RequestBody AmsUserDto amsUserDto) {
        AmsUser savedUser = amsUserService.savedUser(amsUserDto);
        return ResponseEntity.status(201).body(savedUser);
    }



    //PUT - Update user
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<AmsUser> updateUser(@PathVariable("id") int id, @RequestBody AmsUserDto amsUserDto) {
        AmsUser updatedUser = amsUserService.updateUser(id, amsUserDto);
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
    public ResponseEntity<AmsUser> getUserById(@PathVariable("id") int id) {
        AmsUser user = amsUserService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    //GET - Get all users
    @GetMapping("/AllAmsUsers")
    public ResponseEntity<List<AmsUser>> getAllUsers() {
        List<AmsUser> users = amsUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
