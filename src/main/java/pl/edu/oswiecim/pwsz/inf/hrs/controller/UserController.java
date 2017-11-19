package pl.edu.oswiecim.pwsz.inf.hrs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody
    List<UserDto> getAll() {
        List<UserDto> allUsers = userService.findAllDTO();
        LOGGER.info("dupa");
        return allUsers;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addEmployee(@RequestBody String jsonInString) {

        UserDto employeeDto = null;
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(jsonInString);

        try {
            employeeDto = mapper.readValue(reader, UserDto.class);

            LOGGER.info(employeeDto.getUsername() + " " + employeeDto.getEmail());

            User user = userService.convertToEntity(employeeDto);
            userService.saveUser(user);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}