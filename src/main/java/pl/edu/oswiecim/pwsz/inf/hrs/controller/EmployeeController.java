package pl.edu.oswiecim.pwsz.inf.hrs.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.EmployeeDto;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.TrainingDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Employee;
import pl.edu.oswiecim.pwsz.inf.hrs.service.EmployeeService;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/add")
    public void addEmployee(@RequestBody String jsonInString){

//         String jsonInString2 = "{\"firstName\":\"lastName\",\"sex\":\"M\",\"hireDare\":\"2017-12-12\"," +
//

        EmployeeDto employeeDto = null;
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(jsonInString);

        try {
            employeeDto = mapper.readValue(reader, EmployeeDto.class);

            LOGGER.info(employeeDto.getFirstName()+" "+employeeDto.getLastName()+" "+employeeDto.getJob()+
                    " "+employeeDto.getSex()+" "+employeeDto.getHireDate() +
                    " "+employeeDto.getSalary());

            Employee employee = employeeService.convertToEntity(employeeDto);
            employeeService.saveEmployee(employee);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<EmployeeDto> getAll() {
        List<EmployeeDto> allEmployees = employeeService.findAllDTO();
        for(EmployeeDto employeeDto : allEmployees){
            LOGGER.info("Employee id " + employeeDto.getEmployeeId());
            Link selfLink = linkTo(EmployeeController.class).slash(employeeDto.getEmployeeId()).withSelfRel();
            employeeDto.add(selfLink);

        }
        return allEmployees;
    }

    @RequestMapping("/{id}")
    public @ResponseBody EmployeeDto getEmployee(@PathVariable("id") Integer id) {
        EmployeeDto employeeDto = employeeService.convertToDTO(employeeService.findById(id));
        Link selfLink = linkTo(EmployeeController.class).slash(employeeDto.getEmployeeId()).withSelfRel();
        employeeDto.add(selfLink);
        return employeeDto;
    }

}
