package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.UserDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.UserRepo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public User convertToEntity(UserDto employeeDto) throws ParseException {
        return modelMapper.map(employeeDto, User.class);
    }

    @Override
    public UserDto convertToDTO(User employee) {
        return modelMapper.map(employee, UserDto.class);
    }

    @Override
    @Transactional
    public void saveUser(User u) {
        u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        userRepo.save(u);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public List findAllDTO() {
        List usersDTOs = new ArrayList();
        Iterable<User> users = userRepo.findAll();
        for (User user : users) {
            usersDTOs.add(convertToDTO(user));
        }
        return usersDTOs;
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userRepo.delete(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User findById(Integer id) {
        return userRepo.findOne(id);
    }
}
