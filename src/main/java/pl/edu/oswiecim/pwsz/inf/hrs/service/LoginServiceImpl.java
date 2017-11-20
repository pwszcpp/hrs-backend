package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Service("loginAuthorizationService")
public class LoginServiceImpl implements LoginService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    public Boolean logIn(User user){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null) {
            if (userService.findByEmail(user.getEmail()) != null) {
                User existingUser = userService.findByEmail(user.getEmail());
                UserDetails existingUserDetails = userDetailsService.loadUserByUsername(existingUser.getUsername());
                LOGGER.info("Username is valid");

                if (bCryptPasswordEncoder.matches(user.getPassword(),existingUserDetails.getPassword())) {
                    LOGGER.info("Correct username and password");

                    SecurityContextHolder.getContext().setAuthentication
                            (new UsernamePasswordAuthenticationToken(existingUserDetails.getUsername(), existingUserDetails.getPassword(), existingUserDetails.getAuthorities()));
                    LOGGER.info(SecurityContextHolder.getContext().getAuthentication().getName()+"");
                    LOGGER.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities()+"");
                    return true;

                } else {
                    LOGGER.info("invalid password");
                    return false;
                }
            } else {
                LOGGER.info("invalid username or password");
                return false;
            }
        } else {
            LOGGER.info("some user is logged in");
            return false;
        }
    }

    @Override
    public void logOut(HttpServletRequest request, HttpServletResponse response) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
    }
}
