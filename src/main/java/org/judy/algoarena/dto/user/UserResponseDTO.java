package org.judy.algoarena.dto.user;

import org.judy.algoarena.models.Role;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
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
}
