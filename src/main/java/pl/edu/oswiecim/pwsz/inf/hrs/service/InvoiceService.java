package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.InvoiceDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Invoice;

import java.text.ParseException;
import java.util.List;


@Service("invoiceService")
@Transactional(readOnly = true)
public interface InvoiceService {
    Invoice convertToEntity(InvoiceDto invoiceDto) throws ParseException;

    InvoiceDto convertToDTO(Invoice invoice);

    void saveInvoice(Invoice invoice);

    Iterable<Invoice> findAll();

    List findAllDTO();

    Invoice findById(Integer id);

    void deleteInvoice(Integer id);

    void updateInvoice(Integer invoiceId, Invoice invoice) throws ParseException;

    String[] divideJson(String jsonInString);

    Page<Invoice> listAllByPage(Pageable pageable);
}
