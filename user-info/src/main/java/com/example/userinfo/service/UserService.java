package com.example.userinfo.service;

import com.example.userinfo.dto.UserDTO;
import com.example.userinfo.entity.User;
import com.example.userinfo.mapper.UserMapper;
import com.example.userinfo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO addUser(UserDTO userDTO) {
        User savedUser = userRepository.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));
        return UserMapper.INSTANCE.mapUserToUserDTO(savedUser);

    }

    public ResponseEntity<UserDTO> fetchUserDetailsById(Integer userId) {
        Optional<User> fetchedUser =  userRepository.findById(userId);
        return fetchedUser.map(user -> new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDTO(user), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

    }
}
