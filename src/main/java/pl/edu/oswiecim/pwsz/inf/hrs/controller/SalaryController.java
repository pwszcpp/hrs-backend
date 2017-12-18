package pl.edu.oswiecim.pwsz.inf.hrs.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.SalaryDto;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.UserDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Salary;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.UserRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.SalaryService;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(SalaryController.class);

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST)
    public void addSalary(@RequestBody String jsonInString){

        SalaryDto salaryDto = null;
        UserDto userDto= null;
        ObjectMapper mapper = new ObjectMapper();
        LOGGER.info("Salary string " + jsonInString);
        String[] dividedJson = salaryService.divideJson(jsonInString);

        Integer userId = Integer.parseInt(dividedJson[0]);
        String salaryReader = dividedJson[1];


        try{
            salaryDto = mapper.readValue(salaryReader,SalaryDto.class);

            Salary salary = salaryService.convertToEntity(salaryDto);
            salary.setUser(userRepo.findOne(userId));
            salaryService.saveSalary(salary);
            LOGGER.info("Zapisano wyplate dla :" + salary.getUser().getUsername() );
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method =  RequestMethod.GET)
    public @ResponseBody
    List<SalaryDto> getAll(){
        List<SalaryDto> allSalary = salaryService.findAllDTO();
        for(SalaryDto salaryDto : allSalary){
            Link selfLink = linkTo(SalaryController.class).slash(salaryDto.getSalaryId()).withSelfRel();
            salaryDto.add(selfLink);
        }
        return allSalary;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public void upSalary(@PathVariable("id") Integer id, @RequestBody String jsonInString){
        SalaryDto salaryDto = null;
        // UserDto userDto= null;
        ObjectMapper mapper = new ObjectMapper();
        LOGGER.info("Salary string " + jsonInString);
        String[] dividedJson = salaryService.divideJson(jsonInString);

        Integer userId = Integer.parseInt(dividedJson[0]);
        String salaryReader = dividedJson[1];

        try{
            salaryDto = mapper.readValue(salaryReader, SalaryDto.class);
            //LOGGER.info("Z dto wyplata " + jsonInString + "zmiana dla id " + id);

            Salary salary = salaryService.convertToEntity(salaryDto);
            salaryService.updateSalary(id, salary, userId);

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void DeleteSalary(@PathVariable("id") Integer id){
        salaryService.deleteSalary(id);
        LOGGER.info("Deleted salary " + id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    SalaryDto getSalary(@PathVariable("id") Integer id){
        SalaryDto salaryDto = salaryService.convertToDTO(salaryService.findById(id));
        Link selfLink = linkTo(SalaryController.class).slash(salaryDto.getSalaryId()).withSelfRel();
        salaryDto.add(selfLink);

        return salaryDto;
    }
}
