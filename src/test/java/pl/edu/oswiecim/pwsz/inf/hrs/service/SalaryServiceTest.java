package pl.edu.oswiecim.pwsz.inf.hrs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.SalaryDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Salary;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;

import javax.validation.constraints.AssertTrue;
import java.sql.Date;
import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SalaryServiceTest {

    @Autowired
    SalaryService salaryService;
    @Autowired
    UserService userService;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(SalaryServiceTest.class);

    @Test
    public void updateSalaryTest() throws Exception {


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


        //Changes for salary
        Salary salaryChanges = new Salary();
        salaryChanges.setBaseSalary(3500.0);
        salaryChanges.setEmploymentArrangement("full");
        salaryChanges.setSeniority(8);
        salaryChanges.setEmploymentStatus("pracuje dobrze");
        salaryChanges.setUser(user);
        salaryChanges.setSalarySupplement(120.0);



        //Updating salary
        try {
            salaryService.updateSalary(salary.getSalaryId(), salaryChanges, user.getId());
        }catch
                (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Updated salary object
        Salary salaryUpdated = salaryService.findById(salary.getSalaryId());

        LOGGER.info("Changes salary "+ salaryChanges.getBaseSalary()+
                " Updated salary:" + salaryUpdated.getBaseSalary());
        //Comparision between changes salary object and updated salary object
        //Assert.assertEquals(salaryChanges.getBaseSalary(),salaryUpdated.getBaseSalary());
        Assert.assertTrue(salaryChanges.equals(salaryUpdated));

    }
}