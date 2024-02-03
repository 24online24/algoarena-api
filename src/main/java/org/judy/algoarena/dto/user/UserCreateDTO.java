package org.judy.algoarena.dto.user;

import org.judy.algoarena.models.Role;

import lombok.Getter;

@Getter
public class UserCreateDTO extends UserDTO {
    private String password;
    private Role role;

    public UserCreateDTO(String username, String avatar, String email, String password, Role role) {
        super(username, avatar, email);
        this.password = password;
        this.role = role;
    }
}
