package pl.edu.oswiecim.pwsz.inf.hrs.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.RoleRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.RoleService;

import java.util.ArrayList;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public List<String> getAllRoles() {

        List<String> roles = new ArrayList<>();

        roleRepo.findAll().forEach(role -> {
            roles.add(role.getRole());
        });

        return roles;
    }
}
