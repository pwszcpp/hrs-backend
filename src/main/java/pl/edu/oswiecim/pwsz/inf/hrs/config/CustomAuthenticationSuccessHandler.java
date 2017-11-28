package pl.edu.oswiecim.pwsz.inf.hrs.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class CustomAuthenticationSuccessHandler  implements AuthenticationSuccessHandler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_OK);
        final Cookie cookie = new Cookie("Session", UUID.randomUUID().toString());
        response.addCookie(cookie);
        //response.sendRedirect("http://localhost:4200/dashboard");
        LOGGER.info("Authentication OK");

    }
}
