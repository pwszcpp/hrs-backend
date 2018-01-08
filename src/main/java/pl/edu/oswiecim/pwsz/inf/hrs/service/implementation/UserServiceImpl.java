package pl.edu.oswiecim.pwsz.inf.hrs.service.implementation;

import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.UserDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Contractor;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Role;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Position;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.PositionRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.RoleRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.UserRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserService;


import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PositionRepo positionRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepo roleRepo;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public User convertToEntity(UserDto userDto) throws ParseException {
        return modelMapper.map(userDto, User.class);
    }

    @Override
    public UserDto convertToDTO(User employee) {
        return modelMapper.map(employee, UserDto.class);
    }

    @Override
    @Transactional
    public void saveUser(User u) throws Exception {
        if(userRepo.findByEmail(u.getEmail()) == null
                && userRepo.findByUsername(u.getUsername()) == null) {

            u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
            userRepo.save(u);

        } else {
            throw new Exception("User with provided username or email already exists");
        }
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

    @Override
    public Page<User> listAllByPage(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    public void updateUser(Integer userId, User user, Set<Position> newPositions) {


        User existingUser = userRepo.findOne(userId);
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());

//        if(user.getPassword()!= null){
//            existingUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
////            existingUser.setPassword(user.getPassword());
//        }
        existingUser.setAddress(user.getAddress());
        existingUser.setEmploymentStartDate(user.getEmploymentStartDate());
        existingUser.setForename(user.getForename());
        existingUser.setSurname(user.getSurname());
        existingUser.setRole(user.getRole());
        existingUser.setStatus(user.getStatus());
        existingUser.setPassExpire(user.getPassExpire());
        existingUser.setPassChangedDate(user.getPassChangedDate());
        existingUser.setLoginLastSuccess(user.getLoginLastSuccess());
        existingUser.setLoginLastFailed(user.getLoginLastFailed());
        existingUser.setLoginAttemptsFailed(user.getLoginAttemptsFailed());
        existingUser.setPositions(newPositions);

        userRepo.save(existingUser);
    }

    @Override
    public List<String> getRoles(User user) {
        Integer rolesBit = user.getRole();
        Iterable<Role> stringRoles = roleRepo.findAll();
        List<String> roles = new ArrayList<>();
        while(rolesBit > 0) {
            for(Role role : stringRoles){
                if(role.getBit() == Integer.highestOneBit(rolesBit)){
                    roles.add(role.getRole());
                    rolesBit -= Integer.highestOneBit(rolesBit);
                }
            }
        }
        return roles;
    }

    @Override
    public String[] divideJson(String jsonInString) {
        JSONObject jsonObject = new JSONObject(jsonInString);
        JSONObject jsonUser = new JSONObject();
        try {
            Integer positionId = jsonObject.getInt("position_id");
            jsonObject.remove("position_id");

//            jsonUser.put("forename", jsonObject.get("forename"));
//            jsonUser.put("surname", jsonObject.get("surname"));
//            jsonUser.put("username", jsonObject.get("username"));
//            jsonUser.put("email", jsonObject.get("email"));
//
//            if(jsonObject.get("password") != null){
//                jsonUser.put("password", jsonObject.get("password"));
//            }
//
//            jsonUser.put("role", jsonObject.get("role"));
//            jsonUser.put("address", jsonObject.get("address"));
//            jsonUser.put("employmentStartDate", jsonObject.get("employmentStartDate"));

            String userReader = jsonObject.toString();
            return new String[]{positionId.toString(), userReader};

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new String[]{""};
    }
}
