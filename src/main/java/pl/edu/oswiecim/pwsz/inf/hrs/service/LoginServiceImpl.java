package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.edu.oswiecim.pwsz.inf.hrs.controller.LoginController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Service("loginAuthorizationService")
public class LoginServiceImpl implements LoginService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    public void logIn(String username, String password){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

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

    @Override
    public void logOut(HttpServletRequest request, HttpServletResponse response) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
    }
}
