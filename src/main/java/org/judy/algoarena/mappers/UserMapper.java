package org.judy.algoarena.mappers;

import org.judy.algoarena.dto.UserCreateDTO;
import org.judy.algoarena.dto.UserResponseDTO;
import org.judy.algoarena.models.Role;
import org.judy.algoarena.models.User;

public class UserMapper {

    public static UserResponseDTO convertToDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getAvatar(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()
        );
    }

    public static User convertToEntity(UserCreateDTO userCreateDTO, Role role) {
        return new User(
                userCreateDTO.getUsername(),
                userCreateDTO.getAvatar(),
                userCreateDTO.getEmail(),
                userCreateDTO.getPassword(),
                role
        );
    }
}
