package pl.edu.oswiecim.pwsz.inf.hrs.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.ContractorDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Contractor;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.ContractorRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.ContractorService;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/contractors")
public class ContractorController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ContractorController.class);

    @Autowired
    ContractorService contractorService;

    @Autowired
    ContractorRepo contractorRepo;

    @RequestMapping(method = RequestMethod.POST)
    public void addContractor(@RequestBody String jsonInString) {

        ContractorDto contractorDto;
        ObjectMapper mapper = new ObjectMapper();

        StringReader contractorReader = new StringReader(jsonInString);

        LOGGER.info("Z json kontrahent " + contractorReader);

        try {


            contractorDto = mapper.readValue(contractorReader, ContractorDto.class);

            LOGGER.info(contractorDto.getName() + " " + contractorDto.getNip());

            Contractor contractor = contractorService.convertToEntity(contractorDto);
            contractorService.saveContractor(contractor);


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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteContractor(@PathVariable("id") Integer id) {
        contractorService.deleteContractor(id);
        LOGGER.info("Delted contractor " + id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void updContractor(@PathVariable("id") Integer id, @RequestBody String jsonInString) {

        ContractorDto contractorDto;
        ObjectMapper mapper = new ObjectMapper();

        StringReader contractorReader = new StringReader(jsonInString);

        LOGGER.info("Z json kontrahent " + contractorReader);

        try {
            contractorDto = mapper.readValue(contractorReader, ContractorDto.class);


            LOGGER.info(contractorDto.getName() + " " + contractorDto.getNip());

            Contractor contractor = contractorService.convertToEntity(contractorDto);
            contractorService.updateContractor(id, contractor);


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
    public @ResponseBody
    List<ContractorDto> getAll() {
        List<ContractorDto> allContractors = contractorService.findAllDTO();
        for (ContractorDto contractorDto : allContractors) {
            LOGGER.info("Contractor id: " + contractorDto.getContractorId());
            Link selfLink = linkTo(ContractorController.class).slash(contractorDto.getContractorId()).withSelfRel();
            contractorDto.add(selfLink);
        }
        return allContractors;
    }

    @RequestMapping("/{id}")
    public @ResponseBody
    ContractorDto getContractor(@PathVariable("id") Integer id) {
        ContractorDto contractorDto = contractorService.convertToDTO(contractorService.findById(id));
        Link selfLink = linkTo(ContractorController.class).slash(contractorDto.getContractorId()).withSelfRel();
        contractorDto.add(selfLink);
        return contractorDto;
    }


}