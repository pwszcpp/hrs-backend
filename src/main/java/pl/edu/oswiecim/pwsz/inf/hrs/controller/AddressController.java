package pl.edu.oswiecim.pwsz.inf.hrs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.AddressDto;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.EmployeeDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Address;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Employee;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.AddressRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.EmployeeRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.AddressService;
import pl.edu.oswiecim.pwsz.inf.hrs.service.EmployeeService;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(AddressController.class);

    @Autowired
    AddressService addressService;

    @Autowired
    AddressRepo addressRepo;

    @RequestMapping(method = RequestMethod.POST)
    public void addAddress(@RequestBody String jsonInString) {

        AddressDto addressDto = null;
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(jsonInString);

        try {
            addressDto = mapper.readValue(reader, AddressDto.class);

            LOGGER.info(addressDto.getAddressLine() + " " + addressDto.getCity() +
                    " " + addressDto.getStreet() + " " + addressDto.getPostalCode() +
                    " " + addressDto.getCountry());

            Address address = addressService.convertToEntity(addressDto);
            addressService.saveAddress(address);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAddress(@PathVariable("id") Integer id) {
        addressService.deleteAddress(id);
        LOGGER.info("Delted address " + id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void updAddress(@PathVariable("id") Integer id, @RequestBody String jsonInString) {


        AddressDto addressDto = null;
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(jsonInString);

        try {
            addressDto = mapper.readValue(reader, AddressDto.class);

            LOGGER.info(addressDto.getAddressLine() + " " + addressDto.getCity() +
                    " " + addressDto.getStreet() + " " + addressDto.getPostalCode() +
                    " " + addressDto.getCountry());

            Address address = addressService.convertToEntity(addressDto);
            addressService.updateAddress(id, address);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<AddressDto> getAll() {
        List<AddressDto> allAddresses = addressService.findAllDTO();
        for (AddressDto addressDto : allAddresses) {
            LOGGER.info("Address id" + addressDto.getAddressId());
            Link selfLink = linkTo(AddressController.class).slash(addressDto.getAddressId()).withSelfRel();
            addressDto.add(selfLink);

        }
        return allAddresses;
    }

    @RequestMapping("/{id}")
    public @ResponseBody
    AddressDto getAddress(@PathVariable("id") Integer id) {
        AddressDto addressDto = addressService.convertToDTO(addressService.findById(id));
        Link selfLink = linkTo(AddressController.class).slash(addressDto.getAddressId()).withSelfRel();
        addressDto.add(selfLink);
        return addressDto;
    }
}
