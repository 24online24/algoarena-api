package org.judy.algoarena.controllers;

import org.judy.algoarena.models.Role;
import org.judy.algoarena.models.User;
import org.judy.algoarena.repositories.RoleRepository;
import org.judy.algoarena.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping(value = "/")
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

    @GetMapping("/")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PatchMapping("/id}")
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

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
