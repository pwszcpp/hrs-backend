package pl.edu.oswiecim.pwsz.inf.hrs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Role;

@Repository("roleRepository")
public interface RoleRepo extends CrudRepository<Role, Integer>,PagingAndSortingRepository<Role,Integer> {
    Role findByRole(String role);
}
