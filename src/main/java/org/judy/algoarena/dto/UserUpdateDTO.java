package org.judy.algoarena.dto;

public class UserUpdateDTO extends UserDTO {
    private Long id;
    private String password;
    private Long roleId;

    public UserUpdateDTO(Long id, String username, String avatar, String email, String password, Long roleId) {
        super(username, avatar, email);
        this.id = id;
        this.password = password;
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Long getRoleId() {
        return roleId;
    }
}
