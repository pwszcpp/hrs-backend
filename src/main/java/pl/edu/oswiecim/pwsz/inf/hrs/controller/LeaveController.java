package pl.edu.oswiecim.pwsz.inf.hrs.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.InvoiceDto;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.LeaveDto;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.UserDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Leave;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.UserRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.LeaveService;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserService;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(LeaveController.class);


    @PreAuthorize("hasAnyAuthority('Employee')")
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED, reason="Leave created")
    public void addLeave(@RequestBody String jsonInString ){

        LeaveDto leaveDto = null;
        ObjectMapper mapper = new ObjectMapper();
        LOGGER.info("Urlop string " + jsonInString);
       // String[] dividedJson = leaveService.divideJson(jsonInString);

        Integer userId = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
        //String leaveReader = dividedJson[1];

        try{
            leaveDto = mapper.readValue(jsonInString,LeaveDto.class);
            LOGGER.info("Urlop dto createDate " + leaveDto.getCreateDate());
            Leave leave = leaveService.convertToEntity(leaveDto);
            leave.setUser(userRepo.findOne(userId));
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

    @PreAuthorize("hasAnyAuthority('HR manager','HR expert','Accounting manager','General manager','System administrator')")
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

    @PreAuthorize("hasAnyAuthority('Employee')")
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET, value = "/my")
    public @ResponseBody
    List<LeaveDto> getMyLeave(){

        int userId = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();

        List <LeaveDto> allLeave = leaveService.findAllDTO();
        List <LeaveDto> myLeave = new ArrayList<>();

        for(LeaveDto leaveDto : allLeave){
            if(leaveDto.getUser().getId() == userId){
                myLeave.add(leaveDto);
            }
       }
        return myLeave;
    }

    @PreAuthorize("hasAnyAuthority('HR manager','HR expert','General manager','System administrator')")
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason="Leave updated")
    public void upLeave(@PathVariable("id") Integer id, @RequestBody String jsonInString) {
        LeaveDto leaveDto = null;

        ObjectMapper mapper = new ObjectMapper();
        LOGGER.info("Urlop string " + jsonInString);
        String[] dividedJson = leaveService.divideJson(jsonInString);

        Integer userId = Integer.parseInt(dividedJson[0]);
        String leaveReader = dividedJson[1];

        try{
            leaveDto = mapper.readValue(leaveReader,LeaveDto.class);
            LOGGER.info("Urlop dto createDate " + leaveDto.getCreateDate());
            Leave leave = leaveService.convertToEntity(leaveDto);


            leaveService.updateLeave(id,leave,userId);

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

    @PreAuthorize("hasAnyAuthority('HR manager','HR expert','General manager','System administrator')")
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason="Leave deleted")
    public void deleteLeave(@PathVariable("id") Integer id) {
        leaveService.deleteLeave(id);
        LOGGER.info("Delted leave " + id);
    }

    @PreAuthorize("hasAnyAuthority('HR manager','HR expert','General manager','System administrator')")
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    LeaveDto getLeave(@PathVariable("id") Integer id) {
        LeaveDto leaveDto = leaveService.convertToDTO(leaveService.findById(id));
        Link selfLink = linkTo(LeaveController.class).slash(leaveDto.getLeaveId()).withSelfRel();
        leaveDto.add(selfLink);

        return leaveDto;
    }


}
