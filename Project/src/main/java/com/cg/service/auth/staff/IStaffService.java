package com.cg.service.auth.staff;

import com.cg.model.auth.Staff;
import com.cg.model.dto.authDTO.StaffInfoDTO;
import com.cg.model.dto.authDTO.StaffReqDTO;
import com.cg.service.baseService.IGeneralService;

import java.util.Optional;

public interface IStaffService extends IGeneralService<Staff> {
    Optional<StaffInfoDTO> getStaffInfoByUsername(String name);
void create(StaffReqDTO staffReqDTO);
Boolean existByEmail (String email);
void register(StaffReqDTO staffReqDTO);

}
