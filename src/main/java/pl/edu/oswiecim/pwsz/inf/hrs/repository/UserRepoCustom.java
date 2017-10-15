package pl.edu.oswiecim.pwsz.inf.hrs.repository;

import pl.edu.oswiecim.pwsz.inf.hrs.model.User;

public interface UserRepoCustom {
    User findByUsername(String username);
}
