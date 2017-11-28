package pl.edu.oswiecim.pwsz.inf.hrs.service;

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
public class InvoiceServiceImpl implements InvoiceService{

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
        for(Invoice invoice : invoices){
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
        Invoice existingIn = invoiceRepo.findOne(invoiceId);
        existingIn.setDescription(invoice.getDescription());
        existingIn.setGrossAmount(invoice.getGrossAmount());
        existingIn.setNetAmount(invoice.getNetAmount());
        existingIn.setTax(invoice.getTax());
        existingIn.setContractor(contractorRepo.findOne(contractorId));

        invoiceRepo.save(existingIn);

    }
}