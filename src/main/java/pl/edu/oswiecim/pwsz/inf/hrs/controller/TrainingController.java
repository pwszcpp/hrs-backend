package pl.edu.oswiecim.pwsz.inf.hrs.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.TrainingDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Training;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;
import pl.edu.oswiecim.pwsz.inf.hrs.service.TrainingService;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;




@Controller
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

            trainingService.convertToEntity(trainingDto);

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

    @RequestMapping("/list")
    public Iterable <Training> get() {
        return trainingService.findAll();
    }
}
