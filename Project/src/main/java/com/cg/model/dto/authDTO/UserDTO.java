package com.cg.model.dto.authDTO;

import com.cg.model.auth.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    @NotBlank(message = "The email is required")
    @Min(value = 5, message = "User name has at least 5 characters")
    @Max(value = 30, message = "User name has at most 30 characters")
    private String username;

    @NotBlank(message = "The password is required")
    @Min(value = 5, message = "Password has at least 5 characters")
    @Max(value = 30, message = "Password name has at most 30 characters")
    private String password;

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public User toUser(){
        return new User()
                .setId(id)
                .setUsername(username)
                .setPassword(password);
    }
}