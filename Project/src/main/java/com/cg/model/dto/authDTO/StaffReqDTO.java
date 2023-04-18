package com.cg.model.dto.authDTO;

import com.cg.model.auth.Staff;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StaffReqDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;

    public Staff toStaff(){
        return new Staff()
                .setId(id)
                .setEmail(email)
                .setFullName(fullName)
                .setPhoneNumber(phoneNumber);
    }
}
