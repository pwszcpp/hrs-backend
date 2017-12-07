package pl.edu.oswiecim.pwsz.inf.hrs.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Invoice;

@Repository("invoiceRepository")
public interface InvoiceRepo extends CrudRepository<Invoice, Integer> {
}
