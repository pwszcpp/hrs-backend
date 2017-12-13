package pl.edu.oswiecim.pwsz.inf.hrs.model;

import javax.persistence.*;

@Entity
@Table(name = "contractor", schema = "HRS_SCH")
public class Contractor {


    @Id
    @Column(name = "Id")
    @SequenceGenerator(name = "mySeqGen", sequenceName = "HRS_SCH.CONTRACTOR_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    private Integer id;


    @Column(name = "Name")
    private String name;

    @Column(name = "NIP_Number")
    private Long nip;

    @Column(name = "Address")
    private String address;

    @Column(name = "REGON_Number")
    private String regon;

    @Column(name = "Bank_Account_Number")
    private Long accountNumber;

    @Column(name = "Payment_Form")
    private String paymentForm;

    @Column(name = "Active_Taxpayer_VAT_Tax")
    private Boolean activeTaxpayerVATTax;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNip() {
        return nip;
    }

    public void setNip(Long nip) {
        this.nip = nip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPaymentForm() {
        return paymentForm;
    }

    public void setPaymentForm(String paymentForm) {
        this.paymentForm = paymentForm;
    }

    public Boolean getActiveTaxpayerVATTax() {
        return activeTaxpayerVATTax;
    }

    public void setActiveTaxpayerVATTax(Boolean activeTaxpayerVATTax) {
        this.activeTaxpayerVATTax = activeTaxpayerVATTax;
    }

    @Override
    public String toString() {
        return "Contractor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nip=" + nip +
                ", address='" + address + '\'' +
                ", regon='" + regon + '\'' +
                ", accountNumber=" + accountNumber +
                ", paymentForm='" + paymentForm + '\'' +
                ", activeTaxpayerVATTax=" + activeTaxpayerVATTax +
                '}';
    }
}
