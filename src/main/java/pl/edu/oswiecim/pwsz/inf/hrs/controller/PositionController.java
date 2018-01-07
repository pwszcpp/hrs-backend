package pl.edu.oswiecim.pwsz.inf.hrs.controller;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.PositionDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Position;
import pl.edu.oswiecim.pwsz.inf.hrs.service.PositionService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private PositionService positionService;


    private static final Logger LOGGER =
            LoggerFactory.getLogger(PositionController.class);

    @PreAuthorize("hasAnyAuthority('HR manager','HR expert','System administrator')")
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST)
    public void addPosition(@RequestBody String jsonInString){

        PositionDto positionDto = null;

        ObjectMapper mapper = new ObjectMapper();
        LOGGER.info("Position string" + jsonInString);

        try{
            positionDto = mapper.readValue(jsonInString, PositionDto.class);
            LOGGER.info("Position dto name" + positionDto.getName());
            Position position = positionService.convertToEntity(positionDto);
            LOGGER.info("Position name" + position.getName());
            positionService.savePosition(position);
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
    @RequestMapping(method =  RequestMethod.GET)
    public @ResponseBody
    List<PositionDto> getAll(){
        List<PositionDto> allPositions = positionService.findAllDTO();
        for(PositionDto positionDto : allPositions) {
            Link selflink = linkTo(PositionController.class).slash(positionDto.getPositionId()).withSelfRel();
            positionDto.add(selflink);
        }
        return allPositions;
    }

    @PreAuthorize("hasAnyAuthority('HR manager','HR expert','System administrator')")
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public void upPosition(@PathVariable("id") Integer id, @RequestBody String jsonInString){
        PositionDto positionDto = null;

        ObjectMapper objectMapper = new ObjectMapper();


        try {
            positionDto = objectMapper.readValue(jsonInString,PositionDto.class);

            Position position = positionService.convertToEntity(positionDto);

            positionService.updatePosition(id,position);

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

    @PreAuthorize("hasAnyAuthority('HR manager','HR expert','System administrator')")
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePosition(@PathVariable("id") Integer id){
        positionService.deletePosition(id);
        LOGGER.info("Deleted position " + id);
    }

    @PreAuthorize("hasAnyAuthority('HR manager','HR expert','General manager','System administrator')")
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    PositionDto getPosition(@PathVariable("id") Integer id){
        PositionDto positionDto = positionService.convertToDTO(positionService.findById(id));
        Link selfLink = linkTo(PositionController.class).slash(positionDto.getPositionId()).withSelfRel();
        positionDto.add(selfLink);

        return positionDto;
    }




}
