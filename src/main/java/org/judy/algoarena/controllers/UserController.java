package org.judy.algoarena.controllers;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.judy.algoarena.dto.user.UserCreateDTO;
import org.judy.algoarena.dto.user.UserResponseDTO;
import org.judy.algoarena.dto.user.UserUpdateDTO;
import org.judy.algoarena.mappers.UserMapper;
import org.judy.algoarena.models.User;
import org.judy.algoarena.repositories.UserRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping()
    public String addUser(@RequestBody UserCreateDTO userCreateDTO) {
        userRepository.save(UserMapper.convertToEntity(userCreateDTO));
        return "Added new user to repo!";
    }

    @GetMapping()
    public Iterable<UserResponseDTO> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), true)
                .map(UserMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDTO findUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserMapper::convertToDTO).orElse(null);
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@RequestBody UserUpdateDTO userUpdateDTO, @RequestParam Long role_id) {
        Optional<User> userOptional = userRepository.findById(userUpdateDTO.getId());
        if (userOptional.isEmpty()) {
            return null;
        }
        User user = userOptional.get();
        user.setName(userUpdateDTO.getUsername());
        user.setAvatar(userUpdateDTO.getAvatar());
        user.setEmail(userUpdateDTO.getEmail());
        user.setPassword(userUpdateDTO.getPassword());
        user.setRole(userUpdateDTO.getRole());
        userRepository.save(user);
        return UserMapper.convertToDTO(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
