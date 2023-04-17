package com.cg.repository;

import com.cg.model.auth.Staff;
import com.cg.model.dto.authDTO.StaffInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query("SELECT NEW com.cg.model.dto.authDTO.StaffInfoDTO (" +
            "st.fullName, " +
            "st.email, " +
            "st.phoneNumber" +
            ") " +
            "FROM Staff  AS st " +
            "JOIN User AS us " +
            "ON st.user = us " +
            "AND us.username = :username"
    )
    Optional<StaffInfoDTO> getStaffInfoByUserName(@Param("username")String username);

    Boolean existsByEmail(String email);
}
