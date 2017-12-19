package pl.edu.oswiecim.pwsz.inf.hrs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;

@Repository("userRepository")
public interface UserRepo extends CrudRepository<User, Integer>,PagingAndSortingRepository<User,Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
}
