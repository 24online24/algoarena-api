package org.judy.algoarena.auth.services;

import org.judy.algoarena.auth.models.AuthRequest;
import org.judy.algoarena.auth.models.AuthResponse;
import org.judy.algoarena.auth.models.RegisterRequest;
import org.judy.algoarena.models.Role;
import org.judy.algoarena.models.User;
import org.judy.algoarena.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.UriEncoder;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .avatar("https://api.dicebear.com/7.x/initials/svg?seed=" + UriEncoder.encode(request.getUsername()))
                .role(Role.USER)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

    public AuthResponse login(AuthRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        var user = repository.findByEmail(request.getEmail()).get();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

    public void logout(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        var token = authHeader.replace("Bearer ", "");
        request.removeAttribute("Authorization");
        jwtService.invalidateToken(token);
    }

    public User check(String token) {
        String jwt = token.split(" ")[1].trim();
        var email = jwtService.getUserEmailFromJwt(jwt);
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!jwtService.isTokenValid(jwt, user) || jwtService.isTokenExpired(jwt)) {
            throw new RuntimeException("Token expired");
        }

        return user;
    }

}
