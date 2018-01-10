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
        Invoice tomek = new Invoice();
        tomek.setBuyer_name("Ktos");
        tomek.setBuyerAddress("Gdzies");
        tomek.setBuyerNIP(1203938);
        tomek.setGoodsService(12123);
        tomek.setGrossPrice(231231);
        tomek.setNetPrice(98273);
        tomek.setPaymentDate(new Date(2017,12,12));
        tomek.setPaymentMethod("przelew");
        tomek.setQuantity(9822);
        tomek.setSaleDate(new Date(2017, 12, 23));
        tomek.setSeller_name("Oni");
        tomek.setSellerAccountNumber(2341);
        tomek.setSellerAddress("gdzies2");
        tomek.setSellerNIP(123421);
        tomek.setTax(23);

        invoiceService.saveInvoice(tomek);
        int tomekId;
        tomekId = tomek.getId();
        invoiceService.deleteInvoice(tomekId);
        Assert.assertNull(invoiceService.findById(tomekId));
    }
}
