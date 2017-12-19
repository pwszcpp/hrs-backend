package pl.edu.oswiecim.pwsz.inf.hrs.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
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
import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


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

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST)
    public void createTraning(@RequestBody String jsonInString){
       // String jsonInString = "{\"name\":\"trai\",\"owner\":\"Me\",\"startDate\":\"2017-12-12\"," +
         //       "\"endDate\":\"2018-02-09\",\"cost\":\"150000\",\"permission\":\"true\",\"location\":\"NY\"}";

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
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void updateTraning(@PathVariable("id") Integer id, @RequestBody String jsonInString){
       // String jsonInString = "{\"name\":\"trai\",\"owner\":\"Me\",\"startDate\":\"2017-12-12\"," +
         //       "\"endDate\":\"2018-02-09\",\"cost\":\"150000\",\"permission\":\"true\",\"location\":\"NY\"}";

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
        }


    }

//    @RequestMapping(method = RequestMethod.GET)
//    @CrossOrigin(origins = "http://localhost:4200")
//    public @ResponseBody List<TrainingDto> getAll() {
//        List<TrainingDto> allTrainings = trainingService.findAllDTO();
//        for(TrainingDto trainingDto : allTrainings){
//            //LOGGER.info("Training id: " + trainingDto.getTrainingId());
//            Link selfLink = linkTo(TrainingController.class).slash(trainingDto.getTrainingId()).withSelfRel();
////            Link usersLink = linkTo(methodOn(TrainingController.class).getUsers(Integer.parseInt(trainingDto.getTrainingId()))).withRel("users");
////            Link enrolledUsersLink = linkTo(methodOn(TrainingController.class)
////                    .getEnrolledUsers(Integer.parseInt(trainingDto.getTrainingId()))).withRel("enrolledUsers");
//            trainingDto.add(selfLink);
//            //           trainingDto.add(usersLink);
////            trainingDto.add(enrolledUsersLink);
//
//        }
//        return allTrainings;
//    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping( method = RequestMethod.GET)
    Page<TrainingDto> getPage(Pageable pageable) {
        Page<TrainingDto> training = trainingService.listAllByPage(pageable).map(new Converter<Training, TrainingDto>() {
            @Override
            public TrainingDto convert(Training training) {
                return trainingService.convertToDTO(training);
            }
        });
        return training;
    }

    @RequestMapping("/{id}")
    public @ResponseBody TrainingDto getTraining(@PathVariable("id") Integer id) {
        TrainingDto trainingDto = trainingService.convertToDTO(trainingService.findById(id));
        Link selfLink = linkTo(TrainingController.class).slash(trainingDto.getTrainingId()).withSelfRel();
//        Link usersLink = linkTo(methodOn(TrainingController.class).getUsers(id)).withRel("users");
//        Link enrolledUsersLink = linkTo(methodOn(TrainingController.class)
//                .getEnrolledUsers(id)).withRel("enrolledUsers");
        trainingDto.add(selfLink);
    //    trainingDto.add(usersLink);
//        trainingDto.add(enrolledUsersLink);
        return trainingDto;
    }

    @RequestMapping(value="/{trainingId}/enroll",method = RequestMethod.POST)
    public void enrollTraining(@PathVariable("trainingId") Integer trainingId) {

        User user = userService.findByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());
//
//        LOGGER.info("Training Id: " + trainingId+ "user id" + userId );
//
//        Training tr = trainingService.findById(trainingId);
//
//        Set<User> enrolledUsers = tr.getUsers();
//        enrolledUsers.add(userService.findById(userId));
//        tr.setUsers(enrolledUsers);
//        trainingService.saveTraining(tr);

        Training training = trainingService.findById(trainingId);

        UserTraining userTraining = new UserTraining();
        userTraining.setTraining(training);
        userTraining.setUser(user);
        userTraining.setAgreed(false);
        userTraining.setCancelled(false);
        userTraining.setSignDate(new Date());
        userTraining.setNote("elo");

        userTrainingService.saveUserTraining(userTraining);

    }

    @RequestMapping(value="/{trainingId}/enroll",method = RequestMethod.DELETE)
    public void disenroll(@PathVariable("trainingId") Integer trainingId) {

        Integer userId = (userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())).getId();
        userTrainingService.deleteUserTraining(userId, trainingId);

    }

    @RequestMapping(value = "/{id}/enroll",method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Boolean isEnrolled(@PathVariable("id") Integer trainingid) {

        Training training = trainingService.findById(trainingid);
        Set<UserTraining> userTrainings = training.getUserTrainings();

        Integer userId = (userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())).getId();

        for(UserTraining userTraining : userTrainings){
            if(userTraining.getUser().getId() == userId){
                return true;
            }
        }

        return false;

//        Training training = trainingService.findById(id);
////
//        return training.getUserTrainings();
////        List<Integer> assign = new ArrayList<>();
////        Training training = trainingService.findById(id);
////        Set<UserTraining> userTrainings = training.getUserTrainings();
////        for(UserTraining userTraining : userTrainings){
////            assign.add(userTraining.getUser().getId());
////        }
////
////        return assign;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTraining(@PathVariable("id") Integer id){
        trainingService.deleteTraining(id);
    }

//    @RequestMapping(value = "/{trainingId}/permit",method = RequestMethod.POST)
//    public void permitUser(@PathVariable("trainingId") Integer trainingId,
//                                              @RequestBody Integer userId) {
//
////        LOGGER.info("Accepted enrollment - Training Id: " + trainingId+ " user id" + userId );
////        Training tr = trainingService.findById(trainingId);
//////        Set<User>  enrolledUsers = tr.getEnrolledUsers();
////        Set<User>  acceptedUsers = tr.getUsers();
////        User user = userService.findById(userId);
//////        enrolledUsers.remove(user);
////        acceptedUsers.add(user);
////        trainingService.saveTraining(tr);
//    }
//
//    @RequestMapping(value = "/{trainingId}/permit",method = RequestMethod.GET)
//    public @ResponseBody
//    Set<User> getUsers(@PathVariable("trainingId") Integer id) {
////        LOGGER.info("Training Id: " + id );
////        return trainingService.findById(id).getUsers();
//        return null;
//    }


}
