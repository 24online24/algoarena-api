package org.judy.algoarena.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
abstract class UserDTO {
    @NotBlank
    private String username;
    private String avatar;
    @NotBlank
    private String email;
}
