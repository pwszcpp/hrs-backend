package pl.edu.oswiecim.pwsz.inf.hrs.service;

import pl.edu.oswiecim.pwsz.inf.hrs.model.User;

public interface UserService {
    void saveUser(User p);
    User findByUsername(String username);
    Iterable<User> findAll();
}
