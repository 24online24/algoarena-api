package org.judy.algoarena.dto;

public class UserCreateDTO extends UserDTO {
    private String password;
    private Long roleId;

    public UserCreateDTO(String username, String avatar, String email, String password, Long roleId) {
        super(username, avatar, email);
        this.password = password;
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public Long getRoleId() {
        return roleId;
    }
}
