package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.UserDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    User convertToEntity(UserDto userDto) throws ParseException;

    UserDto convertToDTO(User employee);

    void saveUser(User p) throws Exception;

    //User findByUsername(String username);
    User findByUsername(String username);

    Iterable<User> findAll();

    List findAllDTO();

    void deleteUser(Integer id);

    User findByEmail(String email);

    User findById(Integer id);

    Page<User> listAllByPage(Pageable pageable);

    void updateUser(Integer userId, User p);
}
