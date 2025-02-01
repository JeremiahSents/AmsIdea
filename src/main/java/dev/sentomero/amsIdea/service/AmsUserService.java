package dev.sentomero.amsIdea.service;

import dev.sentomero.amsIdea.dto.AmsUserDto;
import dev.sentomero.amsIdea.model.AmsUser;

import java.util.List;

public interface AmsUserService {
    AmsUser savedUser(AmsUserDto user);

    List<AmsUser> getAllUsers();

    AmsUser getUserById(int id);

    AmsUser updateUser(int id, AmsUserDto user);

    void deleteUser(int id);
}
