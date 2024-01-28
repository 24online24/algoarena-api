package org.judy.algoarena.dto;

import org.judy.algoarena.models.Role;

import java.time.LocalDateTime;

public class UserResponseDTO extends UserDTO {
    private Long id;
    private Role role;
    private LocalDateTime createdAt;

    public UserResponseDTO(Long id, String username, String avatar, String email, Role role, LocalDateTime createdAt) {
        super(username, avatar, email);
        this.id = id;
        this.role = role;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }

    public Role getRole() { return role; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
