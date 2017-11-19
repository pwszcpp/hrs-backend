package pl.edu.oswiecim.pwsz.inf.hrs.service;

import pl.edu.oswiecim.pwsz.inf.hrs.dto.UserDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    User convertToEntity(UserDto userDto) throws ParseException;
    UserDto convertToDTO(User employee);
    void saveUser(User p);
    //User findByUsername(String username);
    User findByUsername(String username);
    Iterable<User> findAll();
    List findAllDTO();
}
