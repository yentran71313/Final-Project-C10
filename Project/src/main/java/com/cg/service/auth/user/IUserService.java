package com.cg.service.auth.user;

import com.cg.model.auth.User;
import com.cg.model.dto.authDTO.UserDTO;
import com.cg.service.baseService.IGeneralService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {

    User getByUserName(String name);
    Optional<User> findByUserName(String userName);

    Optional<UserDTO> findUserDTOByUserName(String userName);
    boolean existByUserName(String username);
}
