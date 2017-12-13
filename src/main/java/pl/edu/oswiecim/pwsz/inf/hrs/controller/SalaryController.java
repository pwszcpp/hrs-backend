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
import pl.edu.oswiecim.pwsz.inf.hrs.model.Salary;
import pl.edu.oswiecim.pwsz.inf.hrs.service.SalaryService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(SalaryController.class);

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST)
    public void addSalary(@RequestBody String jsonInString){

        SalaryDto salaryDto = null;
        ObjectMapper mapper = new ObjectMapper();

        LOGGER.info("Salary string " + jsonInString);

        try{
            salaryDto = mapper.readValue(jsonInString,SalaryDto.class);
            LOGGER.info("User ID DTO " + salaryDto.getUserId());
            Salary salary = salaryService.convertToEntity(salaryDto);
            LOGGER.info("User ID  " + salary.getUserId());
            salaryService.saveSalary(salary);

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
        ObjectMapper mapper = new ObjectMapper();
        LOGGER.info("Z jsona wyplata " + jsonInString + " zmiana dla id " + id);

        try{
            salaryDto = mapper.readValue(jsonInString, SalaryDto.class);
            LOGGER.info("Z dto wyplata " + jsonInString + "zmiana dla id " + id);

            Salary salary = salaryService.convertToEntity(salaryDto);

            salaryService.updateSalary(id, salary);

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
