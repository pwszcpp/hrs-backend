package pl.edu.oswiecim.pwsz.inf.hrs.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Role;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserDetailsServiceImpl;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserService;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;

import javax.management.relation.RoleList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/security")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();



    private static final Logger LOGGER =
            LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/logout")
    public String logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }


    @PostMapping("/{username}")
    public void login(@RequestBody String password,
        @PathVariable("username") String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        LOGGER.info("Username: " + username +", password: " + password);


        if(auth != null) {
            if (userService.findByUsername(username) != null) {
                UserDetails user = userDetailsService.loadUserByUsername(username);
                LOGGER.info("Username is valid");

                if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                    LOGGER.info("Correct username and password");

                    SecurityContextHolder.getContext().setAuthentication
                            (new UsernamePasswordAuthenticationToken(username, password));

                } else { LOGGER.info("invalid password"); }
            } else { LOGGER.info("invalid username or password"); }
        }else{LOGGER.info("some user is logged in");}

    }

    @ResponseBody
    @GetMapping("/get-session")
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
