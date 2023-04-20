package com.cg.model.dto.authDTO;

import com.cg.model.auth.Staff;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StaffReqDTO {
    private Long id;
    @NotBlank(message = "Please input Full name")
    private String fullName;
    @NotBlank(message = "Please input email")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
    @NotBlank(message = "Please input phone number")
    @Pattern(regexp = "(0|0[3|5|7|8|9])+([0-9]{9})", message = "Phone number is invalid")
    private String phoneNumber;
    @NotBlank(message = "Please input user name")
    private String username;

    @NotBlank(message = "Please input password")
    @Pattern(regexp = "(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$)", message = "Password Invalid")
    private String password;

    public Staff toStaff() {
        return new Staff()
                .setId(id)
                .setEmail(email)
                .setFullName(fullName)
                .setPhoneNumber(phoneNumber);
    }
}
