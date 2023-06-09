package com.cg.repository;

import com.cg.model.auth.User;
import com.cg.model.dto.authDTO.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    @Query("SELECT NEW com.cg.model.dto.authDTO.UserDTO (" +
            "u.id, " +
            "u.username" +
            ") " +
            "FROM User AS u " +
            "WHERE u.username =?1"
    )
    Optional<UserDTO> findUserDTOByUsername(String username);

    boolean existsByUsername(String username);

}
