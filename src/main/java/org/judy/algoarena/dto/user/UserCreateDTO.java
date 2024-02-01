package org.judy.algoarena.dto.user;

import java.util.Optional;

import org.judy.algoarena.models.Role;

public class UserCreateDTO extends UserDTO {
    private String password;
    private Optional<Role> role;

    public UserCreateDTO(String username, String avatar, String email, String password, Role role) {
        super(username, avatar, email);
        this.password = password;
        this.role = Optional.ofNullable(role);
    }

    public String getPassword() {
        return password;
    }

    public Optional<Role> getRole() {
        return role;
    }
}
