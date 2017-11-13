package pl.edu.oswiecim.pwsz.inf.hrs.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.TrainingDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Training;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;
import pl.edu.oswiecim.pwsz.inf.hrs.service.TrainingService;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping("/trainings")
public class TrainingController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(TrainingController.class);

    @Autowired
    TrainingService trainingService;


    @RequestMapping("/add")
    public void createTraning(@RequestBody String jsonInString){
       // String jsonInString = "{\"name\":\"trai\",\"owner\":\"Me\",\"startDate\":\"2017-12-12\"," +
         //       "\"endDate\":\"2018-02-09\",\"cost\":\"150000\",\"permission\":\"true\",\"location\":\"NY\"}";

        TrainingDto trainingDto = null;

        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(jsonInString);


        try {
            trainingDto = mapper.readValue(reader, TrainingDto.class);

            LOGGER.info(trainingDto.getName()+" "+trainingDto.getLocation()+" "+trainingDto.getOwner()+
                    " "+trainingDto.getCost()+" "+trainingDto.getEndDate() +
                    " "+trainingDto.getStartDate()+" "+trainingDto.getPermission());

            Training training = trainingService.convertToEntity(trainingDto);
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

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<TrainingDto> getAll() {
        List<TrainingDto> allTrainings = trainingService.findAllDTO();
        for(TrainingDto trainingDto : allTrainings){
            LOGGER.info("Training id: " + trainingDto.getTrainingId());
            Link selfLink = linkTo(TrainingController.class).slash(trainingDto.getTrainingId()).withSelfRel();
            Link usersLink = linkTo(methodOn(TrainingController.class).getUsers(Integer.parseInt(trainingDto.getTrainingId()))).withRel("users");
            trainingDto.add(selfLink);
            trainingDto.add(usersLink);


        }
        return allTrainings;
    }

    @RequestMapping("/{id}")
    public @ResponseBody TrainingDto getTraining(@PathVariable("id") Integer id) {
        TrainingDto trainingDto = trainingService.convertToDTO(trainingService.findById(id));
        Link selfLink = linkTo(TrainingController.class).slash(trainingDto.getTrainingId()).withSelfRel();
        Link usersLink = linkTo(methodOn(TrainingController.class).getUsers(id)).withRel("users");
        trainingDto.add(selfLink);
        trainingDto.add(usersLink);
        return trainingDto;
    }

    @RequestMapping("/{id}/users")
    public @ResponseBody
    Set<User> getUsers(@PathVariable("id") Integer id) {
        LOGGER.info("Training Id: " + id );
        return trainingService.findById(id).getUsers();

    }
}
