package org.judy.algoarena.mappers;

import org.judy.algoarena.dto.user.UserCreateDTO;
import org.judy.algoarena.dto.user.UserResponseDTO;
import org.judy.algoarena.models.User;

public class UserMapper {
    public static UserResponseDTO convertToDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(), // username
                user.getAvatar(),
                user.getUsername(), // email
                user.getRole(),
                user.getCreatedAt());
    }

    public static User convertToEntity(UserCreateDTO userCreateDTO) {
        return new User(
                userCreateDTO.getUsername(),
                userCreateDTO.getAvatar(),
                userCreateDTO.getEmail(),
                userCreateDTO.getPassword(),
                userCreateDTO.getRole());
    }
}
