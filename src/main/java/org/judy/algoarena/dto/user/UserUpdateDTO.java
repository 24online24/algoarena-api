package org.judy.algoarena.dto.user;

import org.judy.algoarena.models.Role;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserUpdateDTO extends UserCreateDTO {
    @NotNull
    private Long id;

    public UserUpdateDTO(Long id, String username, String avatar, String email, String password, Role role) {
        super(username, avatar, email, password, role);
        this.id = id;
    }
}
