package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.InvoiceDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Invoice;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.ContractorRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.InvoiceRepo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service("invoiceService")
@Transactional(readOnly = true)
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepo invoiceRepo;
    @Autowired
    ContractorRepo contractorRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Invoice convertToEntity(InvoiceDto invoiceDto) throws ParseException {
        return modelMapper.map(invoiceDto, Invoice.class);
    }

    @Override
    public InvoiceDto convertToDTO(Invoice invoice) {
        return modelMapper.map(invoice, InvoiceDto.class);
    }

    @Override
    @Transactional
    public void saveInvoice(Invoice invoice) {
        invoiceRepo.save(invoice);

    }

    @Override
    public Iterable<Invoice> findAll() {
        return invoiceRepo.findAll();
    }

    @Override
    public List findAllDTO() {
        List invoicesDTOs = new ArrayList();
        Iterable<Invoice> invoices = invoiceRepo.findAll();
        for (Invoice invoice : invoices) {
            invoicesDTOs.add(convertToDTO(invoice));
        }
        return invoicesDTOs;
    }

    @Override
    public Invoice findById(Integer id) {
        return invoiceRepo.findOne(id);
    }

    @Override
    @Transactional
    public void deleteInvoice(Integer id) {
        invoiceRepo.delete(id);
    }

    @Override
    @Transactional
    public void updateInvoice(Integer invoiceId, Invoice invoice, Integer contractorId) throws ParseException {
//        Invoice existingIn = invoiceRepo.findOne(invoiceId);
//        existingIn.setDescription(invoice.getDescription());
//        existingIn.setGrossAmount(invoice.getGrossAmount());
//        existingIn.setNetAmount(invoice.getNetAmount());
//        existingIn.setTax(invoice.getTax());
//        existingIn.setContractor(contractorRepo.findOne(contractorId));
//
//        invoiceRepo.save(existingIn);

    }

    @Override
    public String[] divideJson(String jsonInString) {
        JSONObject jsonObject = new JSONObject(jsonInString);
        JSONObject jsonInvoice = new JSONObject();

        try {
            Integer contractorId = jsonObject.getInt("contractor_id");
//
//            jsonInvoice.put("description", jsonObject.get("description"));
//            jsonInvoice.put("netAmount", jsonObject.get("netAmount"));
//            jsonInvoice.put("grossAmount", jsonObject.get("grossAmount"));
//            jsonInvoice.put("tax", jsonObject.get("tax"));
//            String invoiceReader = jsonInvoice.toString();
//            return new String[]{contractorId.toString(), invoiceReader};

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new String[]{"",""};
    }
}
