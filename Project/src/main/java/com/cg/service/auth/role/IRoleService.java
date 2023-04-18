package com.cg.service.auth.role;

import com.cg.model.auth.Role;
import com.cg.service.baseService.IGeneralService;

import java.util.Optional;

public interface IRoleService extends IGeneralService<Role> {
    Optional<Role> findByCode (String code);
}
