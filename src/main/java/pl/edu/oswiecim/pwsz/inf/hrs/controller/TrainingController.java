package pl.edu.oswiecim.pwsz.inf.hrs.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.TrainingDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Training;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;
import pl.edu.oswiecim.pwsz.inf.hrs.model.UserTraining;
import pl.edu.oswiecim.pwsz.inf.hrs.service.TrainingService;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserService;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserTrainingService;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@RestController
@RequestMapping("/trainings")
public class TrainingController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(TrainingController.class);

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserTrainingService userTrainingService;

    @PreAuthorize("hasAnyAuthority('Instructors','System administrator')")
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED, reason="New training created")
    public void createTraning(@RequestBody String jsonInString){

        TrainingDto trainingDto;

        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(jsonInString);


        try {
            trainingDto = mapper.readValue(reader, TrainingDto.class);

            LOGGER.info(trainingDto.getName()+" "+trainingDto.getLocation()+" "+trainingDto.getCompany()+
                    " "+trainingDto.getCost()+" "+trainingDto.getEndDate() +
                    " "+trainingDto.getStartDate()+" "+trainingDto.getConsent());

            Training training = trainingService.convertToEntity(trainingDto);
            User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            training.setAuthorId(user.getId());
            trainingService.saveTraining(training);

        } catch (JsonGenerationException e) {
        e.printStackTrace();
        } catch (JsonMappingException e) {
        e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @PreAuthorize("hasAnyAuthority('Instructors','HR manager','HR manager','General manager','System administrator')")
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason="Training updated")
    public void updateTraning(@PathVariable("id") Integer id, @RequestBody String jsonInString){

        TrainingDto trainingDto = null;

        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(jsonInString);


        try {
            trainingDto = mapper.readValue(reader, TrainingDto.class);

            LOGGER.info(trainingDto.getName()+" "+trainingDto.getLocation()+" "+trainingDto.getCompany()+
                    " "+trainingDto.getCost()+" "+trainingDto.getEndDate() +
                    " "+trainingDto.getStartDate()+" "+trainingDto.getConsent());

            Training training = trainingService.convertToEntity(trainingDto);
            training.setAuthorId((userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())).getId());
            trainingService.updateTraining(id,training);

        } catch (JsonGenerationException e) {
        e.printStackTrace();
        } catch (JsonMappingException e) {
        e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @PreAuthorize("hasAnyAuthority('Employee')")
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody List<TrainingDto> getAll() {
        List<TrainingDto> allTrainings = trainingService.findAllDTO();
        for(TrainingDto trainingDto : allTrainings){
            Link selfLink = linkTo(TrainingController.class).slash(trainingDto.getTrainingId()).withSelfRel();
            trainingDto.add(selfLink);
        }
        return allTrainings;
    }

    @PreAuthorize("hasAnyAuthority('Employee')")
    @RequestMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody TrainingDto getTraining(@PathVariable("id") Integer id) throws Exception {
        TrainingDto trainingDto = trainingService.convertToDTO(trainingService.findById(id));
        Link selfLink = linkTo(TrainingController.class).slash(trainingDto.getTrainingId()).withSelfRel();
        trainingDto.add(selfLink);

        return trainingDto;
    }

    @PreAuthorize("hasAnyAuthority('Employee')")
    @RequestMapping(value="/{trainingId}/enroll",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason="Enrolled in training")
        public Boolean enrollTraining(@PathVariable("trainingId") Integer trainingId) throws Exception {

        User user = userService.findByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());

        Training training = trainingService.findById(trainingId);
        if((isEnrolled(trainingId) != true) && (training.getNoOfSeats() != 0)) {
            UserTraining userTraining = new UserTraining();
            userTraining.setTraining(training);
            userTraining.setUser(user);
            userTraining.setAgreed(false);
            userTraining.setCancelled(false);
            userTraining.setSignDate(new Date());
//            userTraining.setNote("elo");
            LOGGER.info("Zapisano");

            training.setNoOfSeats(training.getNoOfSeats()-1);
            trainingService.saveTraining(training);

            userTrainingService.saveUserTraining(userTraining);
            return true;
        }else{
            LOGGER.info("Uzytkownik znajduje sie na liście lub brak miejsc");
            return false;
        }

    }

    @PreAuthorize("hasAnyAuthority('Employee')")
    @RequestMapping(value="/{trainingId}/enroll",method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason="Disenrolled from training")
    public void disenroll(@PathVariable("trainingId") Integer trainingId) throws Exception {

        Integer userId = (userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())).getId();
        UserTraining userTraining = userTrainingService.findUserTraining(userId, trainingId);
        Training training = userTraining.getTraining();
        training.setNoOfSeats(training.getNoOfSeats()+1);
        trainingService.updateTraining(trainingId, training);
        userTrainingService.deleteUserTraining(userId, trainingId);

    }

    @PreAuthorize("hasAnyAuthority('Instructors','HR manager','HR manager','General manager','System administrator')")
    @RequestMapping(value = "/{id}/enroll",method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Boolean isEnrolled(@PathVariable("id") Integer trainingid) throws Exception {

        Training training = trainingService.findById(trainingid);
        Set<UserTraining> userTrainings = training.getUserTrainings();

        Integer userId = (userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())).getId();

        for(UserTraining userTraining : userTrainings){
            if(userTraining.getUser().getId() == userId){
                return true;
            }
        }

        return false;
    }

    @PreAuthorize("hasAnyAuthority('Employee')")
    @RequestMapping(value = "/{id}/enrolled",method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Set<UserTraining> enrolledUsers (@PathVariable("id") Integer trainingid) throws Exception {

        Training training = trainingService.findById(trainingid);
        Set<UserTraining> userTrainings = training.getUserTrainings();


        return userTrainings;
    }

    @PreAuthorize("hasAnyAuthority('Instructors','System administrator')")
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason="Training deleted")

    public void deleteTraining(@PathVariable("id") Integer id) throws Exception {
        trainingService.deleteTraining(id);
    }
}
