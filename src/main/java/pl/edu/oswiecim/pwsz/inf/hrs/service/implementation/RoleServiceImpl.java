package pl.edu.oswiecim.pwsz.inf.hrs.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Role;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.RoleRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.RoleService;

import java.util.ArrayList;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Iterable<Role> getAllRoles() {
        return roleRepo.findAll();
    }
}
