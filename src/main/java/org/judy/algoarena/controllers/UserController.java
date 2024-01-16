package org.judy.algoarena.controllers;

import org.judy.algoarena.models.Role;
import org.judy.algoarena.models.User;
import org.judy.algoarena.repositories.RoleRepository;
import org.judy.algoarena.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/add")
    public String addUser(@RequestParam String username, @RequestParam String avatar, @RequestParam String email, @RequestParam String password, @RequestParam Long role_id) {
        Optional<Role> role = roleRepository.findById(role_id);
        if (role.isEmpty()) {
            return "Role not found!";
        }
        User user = new User(username, avatar, email, password, role.get());
        user.setUsername(username);
        userRepository.save(user);
        return "Added new user to repo!";
    }

    @GetMapping("/list")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public User findUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("/find/{username}")
    public User findUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @PatchMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestParam String username, @RequestParam String avatar, @RequestParam String email, @RequestParam String password, @RequestParam Long role_id) {
        Optional<Role> roleOptional = roleRepository.findById(role_id);
        if (roleOptional.isEmpty()) {
            return null;
        }
        Role role = roleOptional.get();
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return null;
        }
        User user = userOptional.get();
        user.setUsername(username);
        user.setAvatar(avatar);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        userRepository.save(user);
        return user;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
