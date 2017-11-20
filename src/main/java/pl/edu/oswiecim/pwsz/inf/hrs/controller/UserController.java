package pl.edu.oswiecim.pwsz.inf.hrs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.UserDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Role;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserService;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{email:.+}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody
    UserDto getUser(@PathVariable("email") String email) {
        LOGGER.info(email);
        UserDto userDto = userService.convertToDTO(userService.findByEmail(email));
        LOGGER.info("działa");
        return userDto;
    }

    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody
    List<UserDto> getAll() {
        List<UserDto> allUsers = userService.findAllDTO();
        LOGGER.info("działa");
        return allUsers;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addUser(@RequestBody String jsonInString) {

        UserDto userDto = null;
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(jsonInString);

        try {
            userDto = mapper.readValue(reader, UserDto.class);

            LOGGER.info(userDto.getUsername() + " " + userDto.getEmail());

            User user = userService.convertToEntity(userDto);
            Role role = new Role();
            role.setRole("USER");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            userService.saveUser(user);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        LOGGER.info("Delted user " + id);
    }
}