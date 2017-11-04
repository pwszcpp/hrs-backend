package pl.edu.oswiecim.pwsz.inf.hrs.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.oswiecim.pwsz.inf.hrs.service.LoginService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping("/security")
public class LoginController {

    @Autowired
    private LoginService loginService;



    private static final Logger LOGGER =
            LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/logout")
    public String logOut (HttpServletRequest request, HttpServletResponse response){
        loginService.logOut(request,response);
        return "redirect:/security/login?logout";
    }


    @PostMapping("/{username}")
    public void login(@RequestBody String password,
        @PathVariable("username") String username) {
        LOGGER.info("Username: " + username +", password: " + password);

        loginService.logIn(username,password);

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
