package org.judy.algoarena.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
abstract class UserDTO {
    private String username;
    private String avatar;
    private String email;
}
