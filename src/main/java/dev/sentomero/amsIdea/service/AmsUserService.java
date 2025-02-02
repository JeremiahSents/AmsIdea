package dev.sentomero.amsIdea.service;

import dev.sentomero.amsIdea.dto.AmsUserDto;
import dev.sentomero.amsIdea.model.AmsUser;

import java.util.List;

public interface AmsUserService {
    AmsUserDto savedUser(AmsUserDto user);

    boolean authenticateUser(String username,String password);


    List<AmsUserDto> getAllUsers();

    AmsUserDto getUserById(int id);

    AmsUserDto updateUser(int id, AmsUserDto user);

    void deleteUser(int id);
}
