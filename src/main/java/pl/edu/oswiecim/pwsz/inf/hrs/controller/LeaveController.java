package pl.edu.oswiecim.pwsz.inf.hrs.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.InvoiceDto;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.LeaveDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Leave;
import pl.edu.oswiecim.pwsz.inf.hrs.service.LeaveService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

//    @Autowired
//    private LeaveRepo leaveRepo;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(LeaveController.class);

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST)
    public void addLeave(@RequestBody String jsonInString ){

        LeaveDto leaveDto = null;
        ObjectMapper mapper = new ObjectMapper();

        LOGGER.info("Urlop string " + jsonInString);

        try{
            leaveDto = mapper.readValue(jsonInString,LeaveDto.class);
            LOGGER.info("Urlop dto createDate " + leaveDto.getCreatDate());
            Leave leave = leaveService.convertToEntity(leaveDto);
            LOGGER.info("Urlop createDate " + leave.getCreateDate());
            leaveService.saveLeave(leave);

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<LeaveDto> getAll(){
        List <LeaveDto> allLeave = leaveService.findAllDTO();
        for(LeaveDto leaveDto : allLeave){
          Link selfLink = linkTo(LeaveController.class).slash(leaveDto.getLeaveId()).withSelfRel();
           leaveDto.add(selfLink);
       }

        return allLeave;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void upLeave(@PathVariable("id") Integer id, @RequestBody String jsonInString) {
        LeaveDto leaveDto = null;
        ObjectMapper mapper = new ObjectMapper();
        LOGGER.info("Z json urlop " + jsonInString +" zmiana dla id " +id);

        try{
            leaveDto = mapper.readValue(jsonInString, LeaveDto.class);
            LOGGER.info("Z dto urlop " + jsonInString +" zmiana dla id " + id);

            Leave leave = leaveService.convertToEntity(leaveDto);

            leaveService.updateLeave(id,leave);

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteLeave(@PathVariable("id") Integer id) {
        leaveService.deleteLeave(id);
        LOGGER.info("Delted leave " + id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    LeaveDto getLeave(@PathVariable("id") Integer id) {
        LeaveDto leaveDto = leaveService.convertToDTO(leaveService.findById(id));
        Link selfLink = linkTo(LeaveController.class).slash(leaveDto.getLeaveId()).withSelfRel();
        leaveDto.add(selfLink);

        return leaveDto;
    }


}