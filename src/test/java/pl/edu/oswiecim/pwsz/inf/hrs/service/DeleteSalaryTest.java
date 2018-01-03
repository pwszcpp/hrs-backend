package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Salary;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;

import java.sql.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

public class DeleteSalaryTest {

    @Autowired
    UserService userService;
    @Autowired
    SalaryService salaryService;
    private static final Logger LOGGER =
            LoggerFactory.getLogger(SalaryServiceTest.class);

    @Test
    public void deleteSalaryTest() throws Exception {
        User user = new User();
        user.setUsername("user4");
        user.setForename("user4");
        user.setSurname("user4");
        user.setEmail("user4@user.pl");
        user.setPassword("user4");
        user.setAddress("dd");
        user.setEmploymentStartDate(new Date(2017,12,6));
        user.setRole(46);
        user.setStatus(true);
        user.setPassExpire(new Date(2017,12,12));
        user.setPassChangedDate(new Date(2017,12,12));
        user.setLoginLastSuccess(new Date(2017,12,12));
        user.setLoginLastFailed(new Date(2017,12,12));
        user.setLoginAttemptsFailed(0);
        userService.saveUser(user);

        // Salary object before update
        Salary salary = new Salary();
        salary.setBaseSalary(2800.0);
        salary.setEmploymentArrangement("na czarno");
        salary.setSeniority(7);
        salary.setEmploymentStatus("pracuje");
        salary.setUser(user);
        salary.setSalarySupplement(120.0);
        salaryService.saveSalary(salary);
        LOGGER.info("Salary before"+ salary.getBaseSalary()+" "+ " for " + salary.getUser().getUsername());

//        userService.deleteUser(user.getId());
//        Assert.assertNotNull(salary);

        salaryService.deleteSalary(salary.getSalaryId());
        Assert.assertNotNull(user);
    }

}



