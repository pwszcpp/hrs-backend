package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Salary;

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
    public void updateSalaryTest() {

        Salary salary = salaryService.findById(193);
        LOGGER.info(salary.getBaseSalary().toString()+" "+ salary.getUser().getUsername());

        Salary salary2 = new Salary();
        salary2.setBaseSalary(2800.0);
        salary2.setEmploymentArrangement("na czarno");
        salary2.setSeniority(7);
        salary2.setEmploymentStatus("pracuje");
        salary2.setUser(userService.findById(897));
        salary2.setSalarySupplement(120.0);

        try {
            salaryService.updateSalary(193, salary2, 897);
        }catch
            (ParseException e) {
            e.printStackTrace();
        }

        Salary salaryUpdated = salaryService.findById(193);
        LOGGER.info(salaryUpdated.getBaseSalary().toString()+" "+ salaryUpdated.getUser().getUsername());

    }
}
