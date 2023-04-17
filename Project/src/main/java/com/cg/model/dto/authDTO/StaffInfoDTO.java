package com.cg.model.dto.authDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StaffInfoDTO {

    @NotBlank(message = "Full name is required")
    @Size(max = 30, message =" Full ame has at most 30 characters")
    @Min(value = 5,message = " Full name has at least 5 characters")
    private String fullName;
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[\\\\w]+@([\\\\w-]+\\\\.)+[\\\\w-]{2,6}$")
    private String email;

    private String phoneNumber;

}
