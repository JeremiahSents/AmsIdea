package dev.sentomero.amsIdea.service.impl;

import dev.sentomero.amsIdea.dto.AmsUserDto;
import dev.sentomero.amsIdea.model.AmsUser;
import dev.sentomero.amsIdea.repository.AmsUserRepository;
import dev.sentomero.amsIdea.service.AmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmsUserServiceImpl implements AmsUserService {

    private final AmsUserRepository amsUserRepository;

    @Autowired
    public AmsUserServiceImpl(AmsUserRepository amsUserRepository) {
        this.amsUserRepository = amsUserRepository;
    }

    @Override
    public AmsUserDto savedUser(AmsUserDto userDto) {
        AmsUser newUser = new AmsUser();
        newUser.setAmsUserFname(userDto.getAmsUserFname());
        newUser.setAmsUserLname(userDto.getAmsUserLname());
        newUser.setAmsUsername(userDto.getAmsUsername());
        newUser.setAmsUserPassword(userDto.getAmsPassword());
        newUser.setTimestamp(LocalDateTime.now());  // Set current timestamp

        AmsUser savedUser = amsUserRepository.save(newUser);

        AmsUserDto savedUserDto = new AmsUserDto();
        savedUserDto.setAmsUserFname(savedUser.getAmsUserFname());
        savedUserDto.setAmsUserLname(savedUser.getAmsUserLname());
        savedUserDto.setAmsUsername(savedUser.getAmsUsername());
        savedUserDto.setAmsPassword(savedUser.getAmsUserPassword());

        return savedUserDto;
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        AmsUser user = amsUserRepository.findByAmsUsername(username);

        if (user != null && user.getAmsUserPassword().equals(password)) {
            return true;  // Authentication successful
        }
        return false;
    }

    @Override
    public List<AmsUserDto> getAllUsers() {
        return amsUserRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private AmsUserDto convertToDto(AmsUser user) {
        AmsUserDto userDto = new AmsUserDto();
        userDto.setAmsUserFname(user.getAmsUserFname());
        userDto.setAmsUserLname(user.getAmsUserLname());
        userDto.setAmsUsername(user.getAmsUsername());
        return userDto;
    }

    @Override
    public AmsUserDto getUserById(int id) {
        AmsUser existingUser = amsUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        AmsUserDto userDto = new AmsUserDto();
        userDto.setAmsUserFname(existingUser.getAmsUserFname());
        userDto.setAmsUserLname(existingUser.getAmsUserLname());
        userDto.setAmsUsername(existingUser.getAmsUsername());
        userDto.setAmsPassword(existingUser.getAmsUserPassword());

        return userDto;
    }

    @Override
    public AmsUserDto updateUser(int id, AmsUserDto userDto) {
        AmsUser existingUser = amsUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        existingUser.setAmsUserFname(userDto.getAmsUserFname());
        existingUser.setAmsUserLname(userDto.getAmsUserLname());
        existingUser.setAmsUsername(userDto.getAmsUsername());
        existingUser.setAmsUserPassword(userDto.getAmsPassword());

        AmsUser savedUser = amsUserRepository.save(existingUser);

        AmsUserDto savedUserDto = new AmsUserDto();
        savedUserDto.setAmsUserFname(savedUser.getAmsUserFname());
        savedUserDto.setAmsUserLname(savedUser.getAmsUserLname());
        savedUserDto.setAmsUsername(savedUser.getAmsUsername());
        savedUserDto.setAmsPassword(savedUser.getAmsUserPassword());

        return savedUserDto;
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


