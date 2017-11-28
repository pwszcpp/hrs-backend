package pl.edu.oswiecim.pwsz.inf.hrs.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.ContractorDto;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.InvoiceDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Contractor;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Invoice;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.ContractorRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.InvoiceRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.ContractorService;
import pl.edu.oswiecim.pwsz.inf.hrs.service.InvoiceService;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    ContractorService contractorService;

    @Autowired
    InvoiceService invoiceService;


    @Autowired
    ContractorRepo contractorRepo;

    @Autowired
    InvoiceRepo invoiceRepo;

    @RequestMapping(method = RequestMethod.POST)
    public void addInvoice(@RequestBody String jsonInString) {

        ContractorDto contractorDto = null;
        InvoiceDto invoiceDto = null;
        ObjectMapper mapper = new ObjectMapper();

        JSONObject jsonObject = new JSONObject(jsonInString);
        JSONObject jsonInvoice = new JSONObject();


        Integer contractorId =  jsonObject.getInt("contractor_id");

        jsonInvoice.put("description",jsonObject.get("description"));
        jsonInvoice.put("netAmount",jsonObject.get("netAmount"));
        jsonInvoice.put("grossAmount",jsonObject.get("grossAmount"));
        jsonInvoice.put("tax",jsonObject.get("tax"));
        LOGGER.info("Z contractor " + contractorId);

        //String contractorReader = jsonContractor.toString();
        String invoiceReader = jsonInvoice.toString();

        //LOGGER.info("Z json kontrahent " + contractorReader);
        LOGGER.info("Z json faktura " + invoiceReader);

        try {
            invoiceDto = mapper.readValue(invoiceReader, InvoiceDto.class);




            //Contractor contractor = contractorService.convertToEntity(contractorDto);
            Invoice invoice = invoiceService.convertToEntity(invoiceDto);
            invoice.setContractor(contractorRepo.findOne(contractorId));
            invoiceService.saveInvoice(invoice);


            LOGGER.info("Faktura " + invoice.getDescription() + "dla kontrahenta " + contractorId);



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

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteInvoice(@PathVariable("id") Integer id) {
        invoiceService.deleteInvoice(id);
        LOGGER.info("Delted invoice " + id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void updInvoice(@PathVariable("id") Integer id, @RequestBody String jsonInString) {
        ContractorDto contractorDto = null;
        InvoiceDto invoiceDto = null;
        ObjectMapper mapper = new ObjectMapper();

        JSONObject jsonObject = new JSONObject(jsonInString);
        JSONObject jsonInvoiceUp = new JSONObject();


        Integer contractorId =  jsonObject.getInt("contractor_id");

        jsonInvoiceUp.put("description",jsonObject.get("description"));
        jsonInvoiceUp.put("netAmount",jsonObject.get("netAmount"));
        jsonInvoiceUp.put("grossAmount",jsonObject.get("grossAmount"));
        jsonInvoiceUp.put("tax",jsonObject.get("tax"));
        LOGGER.info("Z contractor " + contractorId);

        String invoiceReader = jsonInvoiceUp.toString();

        LOGGER.info("Z json faktura " + invoiceReader);

        try {
            invoiceDto = mapper.readValue(invoiceReader, InvoiceDto.class);

            Invoice invoice = invoiceService.convertToEntity(invoiceDto);

            invoiceService.updateInvoice(id,invoice, contractorId);

            LOGGER.info("Faktura " + invoice.getDescription() + "dla kontrahenta " + contractorId);

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
    List<InvoiceDto> getAll() {
        List<InvoiceDto> allInvoices = invoiceService.findAllDTO();
        for (InvoiceDto invoiceDto : allInvoices) {
            LOGGER.info("Invoice id: " + invoiceDto.getInvoiceId());
            Link selfLink = linkTo(InvoiceController.class).slash(invoiceDto.getInvoiceId()).withSelfRel();
            //Link addressLink = linkTo(methodOn(ContractorController.class).getAddress(contractorDto.getContractorId())).withRel("addresses");
            invoiceDto.add(selfLink);
            //contractorDto.add(addressLink);
        }
        return allInvoices;
    }

    @RequestMapping("/{id}")
    public @ResponseBody
    InvoiceDto getInvoice(@PathVariable("id") Integer id) {
        InvoiceDto invoiceDto = invoiceService.convertToDTO(invoiceService.findById(id));
        Link selfLink = linkTo(InvoiceController.class).slash(invoiceDto.getInvoiceId()).withSelfRel();
        invoiceDto.add(selfLink);
        return invoiceDto;
    }

}
