package pl.edu.oswiecim.pwsz.inf.hrs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "name",
        "address",
        "nip_number",
        "regon_number",
        "bank_account_number",
        "payment_form",
        "active_taxpayer_VAT_tax"
})
public class ContractorDto extends ResourceSupport {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("nip_number")
    private Long nip;

    @JsonProperty("address")
    private String address;

    @JsonProperty("regon_number")
    private Long regon;

    @JsonProperty("bank_account_number")
    private String accountNumber;

    @JsonProperty("payment_form")
    private String paymentForm;

    @JsonProperty("active_taxpayer_VAT_tax")
    private Boolean activeTaxpayerVATTax;

    @JsonProperty("id")
    public Integer getContractorId() {
        return id;
    }

    @JsonProperty("id")
    public void setContractorId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("nip_number")
    public Long getNip() {
        return nip;
    }

    @JsonProperty("nip_number")
    public void setNip(Long nip) {
        this.nip = nip;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("regon_number")
    public Long getRegon() {
        return regon;
    }

    @JsonProperty("regon_number")
    public void setRegon(Long regon) {
        this.regon = regon;
    }

    @JsonProperty("bank_account_number")
    public String getAccountNumber() {
        return accountNumber;
    }

    @JsonProperty("bank_account_number")
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @JsonProperty("payment_form")
    public String getPaymentForm() {
        return paymentForm;
    }

    @JsonProperty("payment_form")
    public void setPaymentForm(String paymentForm) {
        this.paymentForm = paymentForm;
    }

    @JsonProperty("active_taxpayer_VAT_tax")
    public Boolean getActiveTaxpayerVATTax() {
        return activeTaxpayerVATTax;
    }

    @JsonProperty("active_taxpayer_VAT_tax")
    public void setActiveTaxpayerVATTax(Boolean activeTaxpayerVATTax) {
        this.activeTaxpayerVATTax = activeTaxpayerVATTax;
    }
}
