package pl.edu.oswiecim.pwsz.inf.hrs.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.UserRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

@Component
public class CustomAuthenticationSuccessHandler  implements AuthenticationSuccessHandler {


    @Autowired
    UserService userService;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_OK);
        final Cookie cookie = new Cookie("Session", UUID.randomUUID().toString());
        response.addCookie(cookie);
        //response.sendRedirect("http://localhost:4200/dashboard");
        LOGGER.info("Authentication OK");
        User user =  userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        LOGGER.info("USER_ID: " + user.getId());

      Date date = new Date(System.currentTimeMillis());
      user.setLoginLastSuccess(date);
    }
}
