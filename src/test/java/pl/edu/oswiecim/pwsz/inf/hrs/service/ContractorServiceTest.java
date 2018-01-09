package pl.edu.oswiecim.pwsz.inf.hrs.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Contractor;

import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ContractorServiceTest {

    @Autowired
    ContractorService contractorService;


    @Test
    public void updateContractorTest() throws ParseException {

        //creating new contractor
        Contractor contractor = new Contractor();
        contractor.setAccountNumber(1235356L);
        contractor.setNip(25151125125125L);
        contractor.setRegon("112315123");
        contractor.setAddress("Górka Małą");
        contractor.setActiveTaxpayerVATTax(true);
        contractor.setName("Franek Sp.ZOO");
        contractor.setPaymentForm("By cash");
        contractorService.saveContractor(contractor);
        System.out.println(contractor.toString());

        //creating a contractor to be swapped with the previous one
        Contractor changedContractor = new Contractor();
        changedContractor.setAccountNumber(654321L);
        changedContractor.setNip(212312512L);
        changedContractor.setRegon("1251234131");
        changedContractor.setAddress("Szczebrzeszyn");
        changedContractor.setActiveTaxpayerVATTax(true);
        changedContractor.setName("Mietek Sp.ZOO");
        changedContractor.setPaymentForm("By card");
        contractorService.saveContractor(changedContractor);
        System.out.println(changedContractor.toString());

        //replacing first contractor with changedContractor
        contractorService.updateContractor(contractor.getId(),changedContractor);
        System.out.println(contractor.toString());
        //Can't assert true for whole object because the ID's are different, comparing by using Regon as it is an Unique entry
        Assert.assertTrue(changedContractor.getRegon().equals(contractor.getRegon()));

    }
    @Test
    public void entryIsNullAfterDeletion() {
        Contractor contractor = new Contractor();
        contractor.setAccountNumber(1235356L);
        contractor.setNip(25151125125125L);
        contractor.setRegon("112315123");
        contractor.setAddress("Górka Małą");
        contractor.setActiveTaxpayerVATTax(true);
        contractor.setName("Franek Sp.ZOO");
        contractor.setPaymentForm("By cash");
        contractorService.saveContractor(contractor);
        System.out.println(contractor.toString());

        int contractorId = contractor.getId();
        contractorService.deleteContractor(contractor.getId());

        Assert.assertNull(contractorService.findById(contractorId));

    }@Test
    public void entryIsNotNullAfterAdding() {
        Contractor contractor = new Contractor();
        contractor.setAccountNumber(1235356L);
        contractor.setNip(25151125125125L);
        contractor.setRegon("112315123");
        contractor.setAddress("Górka Małą");
        contractor.setActiveTaxpayerVATTax(true);
        contractor.setName("Franek Sp.ZOO");
        contractor.setPaymentForm("By cash");
        contractorService.saveContractor(contractor);
        System.out.println(contractor.toString());

        int contractorId = contractor.getId();

        Assert.assertNotNull(contractorService.findById(contractorId));




    }
}