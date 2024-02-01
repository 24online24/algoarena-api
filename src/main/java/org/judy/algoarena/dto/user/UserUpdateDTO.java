package org.judy.algoarena.dto.user;

import org.judy.algoarena.models.Role;

public class UserUpdateDTO extends UserDTO {
    private Long id;
    private String password;
    private Role role;

    public UserUpdateDTO(Long id, String username, String avatar, String email, String password, Role role) {
        super(username, avatar, email);
        this.id = id;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
