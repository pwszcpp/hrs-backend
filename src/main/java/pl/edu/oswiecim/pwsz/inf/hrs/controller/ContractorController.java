package pl.edu.oswiecim.pwsz.inf.hrs.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
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

    @PreAuthorize("hasAnyAuthority('System administrator', 'CRM manager', 'General manager', 'Accounting manager')")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED, reason="Contractor added")
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

    @PreAuthorize("hasAnyAuthority('System administrator', 'CRM manager', 'General manager', 'Accounting manager')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason="Contractor deleted")
    public void deleteContractor(@PathVariable("id") Integer id) {
        contractorService.deleteContractor(id);
        LOGGER.info("Delted contractor " + id);

    }

    @PreAuthorize("hasAnyAuthority('System administrator', 'CRM manager', 'General manager', 'Accounting manager')")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason="Contractor Updated")
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


    @PreAuthorize("hasAnyAuthority('System administrator', 'CRM manager', 'General manager', 'Accounting manager')")
    @CrossOrigin(origins = "http://localhost:4200")
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

    @PreAuthorize("hasAnyAuthority('System administrator', 'CRM manager', 'General manager', 'Accounting manager')")
    @RequestMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ContractorDto getContractor(@PathVariable("id") Integer id) {
        ContractorDto contractorDto = contractorService.convertToDTO(contractorService.findById(id));
        Link selfLink = linkTo(ContractorController.class).slash(contractorDto.getContractorId()).withSelfRel();
        contractorDto.add(selfLink);
        return contractorDto;
    }


}