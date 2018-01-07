package pl.edu.oswiecim.pwsz.inf.hrs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.binding.ObjectExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.UserDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.*;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.PositionRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.RoleService;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserService;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PositionRepo positionRepo;

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAnyAuthority('Employee')")
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    UserDto getUser() {
        UserDto userDto = userService.convertToDTO(userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return userDto;
    }

    @PreAuthorize("hasAnyAuthority('Employee')")
    @RequestMapping(value = "/getRole", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<String> getRole(){
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return userService.getRoles(user);
    }

    @PreAuthorize("hasAnyAuthority('HR manager','HR manager','General manager','System administrator')")
    @RequestMapping(value = "/getRoles", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Iterable<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @PreAuthorize("hasAnyAuthority('HR manager','HR manager','General manager','System administrator','CRM manager','Accounting manager','Instructors')")
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody
    List<UserDto> getAll() {
        List<UserDto> allUsers = userService.findAllDTO();
        return allUsers;
    }

    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(value = HttpStatus.CREATED, reason="New User created")
    @PreAuthorize("hasAnyAuthority('HR manager','HR manager','General manager','System administrator')")
    public void addUser(@RequestBody String jsonInString) {

        UserDto userDto = null;
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(jsonInString);

        String[] dividedJson = userService.divideJson(jsonInString);
        Integer position_Id  = Integer.parseInt(dividedJson[0]);
        String userReader = dividedJson[1];

        try {
            userDto = mapper.readValue(userReader, UserDto.class);

            LOGGER.info(userDto.getUsername() + " " + userDto.getEmail());

            User user = userService.convertToEntity(userDto);
            user.setStatus(true);
            Position position = positionRepo.findOne(position_Id);
            Set<Position> positions = new HashSet<>();
            positions.add(position);
            user.setPositions(positions);
            userService.saveUser(user);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(value = HttpStatus.CREATED, reason="New User created")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyAuthority('HR manager','HR manager','General manager','System administrator')")
    public void upUser(@PathVariable("id") Integer id,@RequestBody String jsonInString) {

        UserDto userDto = null;
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(jsonInString);

        String[] dividedJson = userService.divideJson(jsonInString);
        Integer position_Id  = Integer.parseInt(dividedJson[0]);
        String userReader = dividedJson[1];

        try {
            userDto = mapper.readValue(userReader, UserDto.class);

            LOGGER.info(userDto.getUsername() + " " + userDto.getEmail());

            User user = userService.convertToEntity(userDto);
            user.setStatus(true);
            Position position = positionRepo.findOne(position_Id);
            Set<Position> positions = new HashSet<>();
            positions.add(position);
            user.setPositions(positions);
            userService.updateUser(id,user,positions);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @PreAuthorize("hasAnyAuthority('HR manager','HR manager','General manager','System administrator')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason="User deleted")
    public void deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        LOGGER.info("Delted user " + id);
    }

    @PreAuthorize("hasAnyAuthority('Employee')")
    @RequestMapping(value = "/getTrainings", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Integer> getTrainings() {
        Set<UserTraining> userTrainings = (userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())).getUserTrainings();
        List<Integer> ids = userTrainings.stream().map(UserTraining::getTraining).map(Training::getId).collect(Collectors.toList());
        return ids;
    }
}

