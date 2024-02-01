package org.judy.algoarena.dto.user;

public abstract class UserDTO {
    private String username;
    private String avatar;
    private String email;

    public UserDTO(String username, String avatar, String email) {
        this.username = username;
        this.avatar = avatar;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
