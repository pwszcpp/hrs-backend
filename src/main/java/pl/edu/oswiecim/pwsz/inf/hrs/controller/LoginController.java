package pl.edu.oswiecim.pwsz.inf.hrs.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/security")
public class LoginController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(LoginController.class);


    @PostMapping("/{username}")
    public void login(@RequestBody String password,
        @PathVariable("username") String username) {
        LOGGER.info("Username: " + username +", password: " + password);

        SecurityContextHolder.getContext().setAuthentication
                (new UsernamePasswordAuthenticationToken(username, password));

    }

    @ResponseBody
    @GetMapping("/get-session")
    @PreAuthorize("hasRole('USER')")
    public String session(){
        return UUID.randomUUID().toString();



    }


}
