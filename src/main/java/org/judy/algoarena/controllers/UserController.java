package org.judy.algoarena.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.judy.algoarena.dto.submission.SubmissionResponseDTO;
import org.judy.algoarena.auth.models.AuthResponse;
import org.judy.algoarena.auth.services.AuthService;
import org.judy.algoarena.dto.user.UserCreateDTO;
import org.judy.algoarena.dto.user.UserResponseDTO;
import org.judy.algoarena.dto.user.UserUpdateDTO;
import org.judy.algoarena.mappers.SubmissionMapper;
import org.judy.algoarena.mappers.UserMapper;
import org.judy.algoarena.models.User;
import org.judy.algoarena.repositories.SubmissionRepository;
import org.judy.algoarena.repositories.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final SubmissionRepository submissionRepository;

    public UserController(UserRepository userRepository, SubmissionRepository submissionRepository, AuthService authService) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.submissionRepository = submissionRepository;
    }

    @PostMapping()
    public String addUser(@RequestBody UserCreateDTO userCreateDTO) {
        userCreateDTO.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
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
    public UserResponseDTO findUserById(@PathVariable @NonNull Long id) {
        return UserMapper.convertToDTO(userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id)));
    }

    @GetMapping("/{id}/submissions")
    public Iterable<SubmissionResponseDTO> findUserSubmissions(@PathVariable @NonNull Long id) {
        return submissionRepository.findByAuthorId(id).stream().map(SubmissionMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(userUpdateDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userUpdateDTO.getId()));

        user.setName(userUpdateDTO.getUsername());
        user.setAvatar(userUpdateDTO.getAvatar());
        user.setEmail(userUpdateDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        user.setRole(userUpdateDTO.getRole());
        userRepository.save(user);
        return UserMapper.convertToDTO(user);
    }

    @PutMapping("/{id}/profile")
    public AuthResponse updateProfile(
            @PathVariable @NonNull Long id,
            @RequestParam("email") String email,
            @RequestParam("username") String username,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        user.setEmail(email);
        user.setName(username);
        if (avatar != null && !avatar.isEmpty()) {
            try {
                Path publicDirectoryPath = Paths.get("src", "main", "resources", "static");
                Files.createDirectories(publicDirectoryPath);
                Path avatarPath = publicDirectoryPath.resolve(avatar.getOriginalFilename());
                avatar.transferTo(avatarPath);
                user.setAvatar("http://localhost:8080/" + avatar.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        userRepository.save(user);
        return new AuthResponse(authService.updateProfile(user));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable @NonNull Long id) {
        userRepository.deleteById(id);
    }
}
