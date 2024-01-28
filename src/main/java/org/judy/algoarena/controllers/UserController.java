package org.judy.algoarena.controllers;

import org.judy.algoarena.dto.UserCreateDTO;
import org.judy.algoarena.dto.UserResponseDTO;
import org.judy.algoarena.dto.UserUpdateDTO;
import org.judy.algoarena.mappers.UserMapper;
import org.judy.algoarena.models.Role;
import org.judy.algoarena.models.User;
import org.judy.algoarena.repositories.RoleRepository;
import org.judy.algoarena.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping()
    public String addUser(@RequestBody UserCreateDTO userCreateDTO) {
        Optional<Role> role = roleRepository.findById(userCreateDTO.getRoleId());
        if (role.isEmpty()) {
            return "Role not found!";
        }
        userRepository.save(UserMapper.convertToEntity(userCreateDTO, role.get()));
        return "Added new user to repo!";
    }

    @GetMapping()
    public Iterable<UserResponseDTO> getUsers()
    {
        return StreamSupport.stream(userRepository.findAll().spliterator(), true)
                .map(UserMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDTO findUserById(@PathVariable Long id)
    {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserMapper::convertToDTO).orElse(null);
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@RequestBody UserUpdateDTO userUpdateDTO, @RequestParam Long role_id) {
        Optional<Role> roleOptional = roleRepository.findById(role_id);
        if (roleOptional.isEmpty()) {
            return null;
        }
        Role role = roleOptional.get();
        Optional<User> userOptional = userRepository.findById(userUpdateDTO.getId());
        if (userOptional.isEmpty()) {
            return null;
        }
        User user = userOptional.get();
        user.setUsername(userUpdateDTO.getUsername());
        user.setAvatar(userUpdateDTO.getAvatar());
        user.setEmail(userUpdateDTO.getEmail());
        user.setPassword(userUpdateDTO.getPassword());
        user.setRole(role);
        userRepository.save(user);
        return UserMapper.convertToDTO(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
