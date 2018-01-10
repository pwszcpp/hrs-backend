package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Invoice;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class InvoiceServiceTest {

    @Autowired
    InvoiceService invoiceService;

    @Test
    public void DeleteInvoice() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setBuyer_name("Ktos");
        invoice.setBuyerAddress("Gdzies");
        invoice.setBuyerNIP(1203938);
        invoice.setGoodsService(12123);
        invoice.setGrossPrice(231231);
        invoice.setNetPrice(98273);
        invoice.setPaymentDate(new Date(2017,12,12));
        invoice.setPaymentMethod("przelew");
        invoice.setQuantity(9822);
        invoice.setSaleDate(new Date(2017, 12, 23));
        invoice.setSeller_name("Oni");
        invoice.setSellerAccountNumber(2341);
        invoice.setSellerAddress("gdzies2");
        invoice.setSellerNIP(123421);
        invoice.setTax(23);

        invoiceService.saveInvoice(invoice);
        int tmpId;
        tmpId = invoice.getId();
        invoiceService.deleteInvoice(tmpId);
        Assert.assertNull(invoiceService.findById(tmpId));
    }
}
