package com.cg.service.auth.staff;

import com.cg.model.auth.Role;
import com.cg.model.auth.Staff;
import com.cg.model.auth.User;
import com.cg.model.dto.authDTO.StaffInfoDTO;
import com.cg.model.dto.authDTO.StaffReqDTO;
import com.cg.repository.RoleRepository;
import com.cg.repository.StaffRepository;
import com.cg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffService implements IStaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<StaffInfoDTO> getStaffInfoByUsername(String name) {
        return staffRepository.getStaffInfoByUserName(name);
    }

    @Override
    public void create(StaffReqDTO staffReqDTO) {
        User user = new User();
        user.setId(staffReqDTO.getId());
        user.setUsername(staffReqDTO.getUsername());
        user.setPassword(passwordEncoder.encode(staffReqDTO.getPassword()));
        userRepository.save(user);

        Staff staff = staffReqDTO.toStaff();
        staffRepository.save(staff);
    }

    @Override
    public Boolean existByEmail(String email) {
        return staffRepository.existsByEmail(email);
    }

    @Override
    public void register(StaffReqDTO staffReqDTO) {

        Staff staff = staffReqDTO.toStaff();
        User user = new User();
        user.setId(null);
        user.setUsername(staffReqDTO.getUsername());
        user.setPassword(passwordEncoder.encode(staffReqDTO.getPassword()));
        Optional<Role> roleOptional = roleRepository.findByCode("USER");
        user.setRole(roleOptional.get());
        user = userRepository.save(user);
        staff.setUser(user);
        staffRepository.save(staff);
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Optional<Staff> findById(Long id) {
        return staffRepository.findById(id);
    }

    @Override
    public Staff save(Staff staff) {
        return staffRepository.save(staff);
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
