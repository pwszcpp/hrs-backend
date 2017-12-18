package pl.edu.oswiecim.pwsz.inf.hrs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.UserDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Role;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Training;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;
import pl.edu.oswiecim.pwsz.inf.hrs.model.UserTraining;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserService;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @RequestMapping(value = "/{email:.+}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody
    Integer getUser(@PathVariable("email") String email) {
        LOGGER.info(email);
        UserDto userDto = userService.convertToDTO(userService.findByEmail(email));
        Integer id = userDto.getUserId();
        //LOGGER.info("działa");
        return id;
    }

    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody
    List<UserDto> getAll() {
        List<UserDto> allUsers = userService.findAllDTO();
        //LOGGER.info("działa get");
        return allUsers;
    }

    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200")
    public void addUser(@RequestBody String jsonInString) {

        UserDto userDto = null;
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(jsonInString);

        try {
            userDto = mapper.readValue(reader, UserDto.class);

            LOGGER.info(userDto.getUsername() + " " + userDto.getEmail());

            User user = userService.convertToEntity(userDto);
            user.setRole("USER");
            user.setStatus(true);
            userService.saveUser(user);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        LOGGER.info("Delted user " + id);
    }

    @RequestMapping(value = "/getTrainings", method = RequestMethod.GET)
    public List<Integer> getTrainings() {
        Set<UserTraining> userTrainings = (userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())).getUserTrainings();
        List<Integer> ids = userTrainings.stream().map(UserTraining::getTraining).map(Training::getId).collect(Collectors.toList());
        return ids;
    }

    @RequestMapping(value = "/getID", method = RequestMethod.GET)
    public Integer getID() {
        LOGGER.info("USER_ID: " +(userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())).getId());
        return (userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())).getId();
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public UserDto getUserById(@PathVariable("id") Integer id) {
//        UserDto userDto = userService.convertToDTO(userService.findById(id));
//        //LOGGER.info("działa");
//        return userDto;
//    }
}

