package dev.sentomero.amsIdea.service.impl;

import dev.sentomero.amsIdea.dto.AmsUserDto;
import dev.sentomero.amsIdea.model.AmsUser;
import dev.sentomero.amsIdea.repository.AmsUserRepository;
import dev.sentomero.amsIdea.service.AmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AmsUserServiceImpl implements AmsUserService {

    private final AmsUserRepository amsUserRepository;

    @Autowired
    public AmsUserServiceImpl(AmsUserRepository amsUserRepository) {
        this.amsUserRepository = amsUserRepository;
    }

    @Override
    public AmsUser savedUser(AmsUserDto userDto) {
        if (userDto.getAmsEmail() == null || userDto.getAmsEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("User email cannot be null or empty");
        }

        AmsUser newUser = new AmsUser();
        newUser.setAmsUserFname(userDto.getAmsUserFname());
        newUser.setAmsUserLname(userDto.getAmsUserLname());
        newUser.setAmsUsername(userDto.getAmsUsername());
        newUser.setAmsUserEmail(userDto.getAmsEmail());
        newUser.setAmsUserPassword(userDto.getAmsPassword());
        newUser.setTimestamp(LocalDateTime.now());  // Set current timestamp

        return amsUserRepository.save(newUser);
    }

    @Override
    public List<AmsUser> getAllUsers() {
        return amsUserRepository.findAll();
    }

    @Override
    public AmsUser getUserById(int id) {
        return amsUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    @Override
    public AmsUser updateUser(int id, AmsUserDto userDto) {
        AmsUser existingUser = amsUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        existingUser.setAmsUserEmail(userDto.getAmsEmail());
        existingUser.setAmsUserFname(userDto.getAmsUserFname());
        existingUser.setAmsUserLname(userDto.getAmsUserLname());
        existingUser.setAmsUsername(userDto.getAmsUsername());
        existingUser.setAmsUserPassword(userDto.getAmsPassword());
        return amsUserRepository.save(existingUser);
    }

    @Override
    public void deleteUser(int id) {
        if (amsUserRepository.existsById(id)) {  // Changed to if EXISTS
            amsUserRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }

    }
}


