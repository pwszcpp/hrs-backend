package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserService;

import java.sql.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test(expected = Exception.class)
    public void saveUser_ThrowsException_IfUsernameOrEmailAlreadyExists() throws Exception {

        User user = new User();
        user.setUsername("user2");
        user.setEmail("user2@user.pl");
        user.setPassword("user2");
        user.setAddress("dd");
        user.setEmploymentStartDate(new Date(2017,12,6));
        user.setTaxOffice("OSW");
        user.setRole("USER");
        user.setStatus(true);
        user.setPassExpire(new Date(2017,12,12));
        user.setPassChangedDate(new Date(2017,12,12));
        user.setLoginLastSuccess(new Date(2017,12,12));
        user.setLoginLastFailed(new Date(2017,12,12));
        user.setLoginAttemptsFailed(0);
        user.setPosition("ddd");

        userService.saveUser(user);
        userService.saveUser(user);

    }
}
