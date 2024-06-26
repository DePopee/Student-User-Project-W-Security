package com.example.demo.Service;

import com.example.demo.Custom.EmailAlreadyInUseException;
import com.example.demo.Custom.UsernameAlreadyInUseException;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserEntity user) {
        try {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new EmailAlreadyInUseException("Email already in use");
            }

            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                throw new UsernameAlreadyInUseException("Username already in use");
            }

            user.setDateCreated(LocalDate.now());
            user.setTimeCreated(LocalTime.now());
            return userRepository.save(user);
        } catch (Exception e) {
            log.error("Error occurred while creating user: {}", e.getMessage());
            throw e; // Rethrow the exception for global exception handling
        }
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
