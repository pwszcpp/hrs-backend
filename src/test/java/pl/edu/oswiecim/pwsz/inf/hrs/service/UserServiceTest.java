package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserService;

import javax.transaction.Transactional;
import java.sql.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test(expected = Exception.class)
    public void saveUser_ThrowsException_IfUsernameOrEmailAlreadyExists() throws Exception {

        User user = new User();
        user.setUsername("user4");
        user.setEmail("user4@user.pl");
        user.setPassword("user4");
        user.setAddress("dd");
        user.setEmploymentStartDate(new Date(2017,12,6));
        user.setRole(2);
        user.setStatus(true);
        user.setPassExpire(new Date(2017,12,12));
        user.setPassChangedDate(new Date(2017,12,12));
        user.setLoginLastSuccess(new Date(2017,12,12));
        user.setLoginLastFailed(new Date(2017,12,12));
        user.setLoginAttemptsFailed(0);
//        user.setPosition("ddd");

        userService.saveUser(user);
        userService.saveUser(user);

    }
    @Test
    public void findById_ReturnsNull_AfterDeleteUser() throws Exception {

        User user = new User();
        user.setUsername("user5");
        user.setForename("user5");
        user.setSurname("user5");
        user.setEmail("user5@user.pl");
        user.setPassword("user5");
        user.setAddress("MW");
        user.setEmploymentStartDate(new Date(2017,12,6));
        user.setRole(46);
        user.setStatus(true);
        user.setPassExpire(new Date(2017,12,12));
        user.setPassChangedDate(new Date(2017,12,12));
        user.setLoginLastSuccess(new Date(2017,12,12));
        user.setLoginLastFailed(new Date(2017,12,12));
        user.setLoginAttemptsFailed(0);
        userService.saveUser(user);
        int temporaryId;
        temporaryId = user.getId();
        userService.deleteUser(temporaryId);
        Assert.assertNull(userService.findById(temporaryId));
    }
}
