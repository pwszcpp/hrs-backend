package pl.edu.oswiecim.pwsz.inf.hrs.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.oswiecim.pwsz.inf.hrs.service.LoginService;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@RestController
@RequestMapping("/security")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/logout")
    public String logOut (HttpServletRequest request, HttpServletResponse response){
        loginService.logOut(request,response);
        return "redirect:/security/login?logout";
    }


//    @RequestMapping("/login")
//    @CrossOrigin(origins = "http://localhost:4200")
//    public @ResponseBody Boolean login(@RequestBody String jsonInString) throws IOException, ParseException {
//
//        UserDto userDto = null;
//        ObjectMapper mapper = new ObjectMapper();
//        StringReader reader = new StringReader(jsonInString);
//
//        userDto = mapper.readValue(reader, UserDto.class);
//        User user = userService.convertToEntity(userDto);
//        LOGGER.info("login");
//
//        return loginService.logIn(user);
//
//    }

    @RequestMapping("/get-session")
    @CrossOrigin(origins = "http://localhost:4200")
    //@PreAuthorize("hasAnyAuthority('USER')")
    public String session(){
        return UUID.randomUUID().toString();
    }

    @ResponseBody
    @GetMapping("/get-role")
    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    public String role(){
        return "roles: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }


}
