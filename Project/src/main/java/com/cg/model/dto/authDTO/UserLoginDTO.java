package com.cg.model.dto.authDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserLoginDTO {

    @NotBlank(message = "Please input user name ! ")
    private String username;
    @NotBlank(message = "Please input password ! ")
    @Pattern(regexp = "(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$)", message = "Password Invalid")
    private String password;
}
