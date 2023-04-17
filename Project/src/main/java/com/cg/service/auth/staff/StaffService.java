package com.cg.service.auth.staff;

import com.cg.model.auth.Staff;
import com.cg.model.dto.authDTO.StaffInfoDTO;
import com.cg.model.dto.authDTO.StaffReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffService implements IStaffService {
    @Autowired
    private Staff

    @Override
    public Optional<StaffInfoDTO> getStaffInfoByUsername(String name) {
        return Optional.empty();
    }

    @Override
    public void create(StaffReqDTO staffReqDTO) {

    }

    @Override
    public Boolean existByEmail(String email) {
        return null;
    }

    @Override
    public void register(StaffReqDTO staffReqDTO) {

    }

    @Override
    public List<Staff> findAll() {
        return null;
    }

    @Override
    public Optional<Staff> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Staff save(Staff staff) {
        return null;
    }

    @Override
    public void deleted() {

    }

    @Override
    public void deletedById(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }
}
