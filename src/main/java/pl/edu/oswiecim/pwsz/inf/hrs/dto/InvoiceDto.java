package pl.edu.oswiecim.pwsz.inf.hrs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Contractor;

import javax.annotation.Generated;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "description",
        "netAmount",
        "grossAmount",
        "tax",
        "contractor"
})
public class InvoiceDto extends ResourceSupport {


    @JsonProperty("id")
    private String id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("netAmount")
    private double netAmount;
    @JsonProperty("grossAmount")
    private double grossAmount;
    @JsonProperty("tax")
    private double tax;
    @JsonProperty("contractor")
    private Contractor contractor;

    @JsonProperty("id")
    public String getInvoiceId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("netAmount")
    public double getNetAmount() {
        return netAmount;
    }

    @JsonProperty("netAmount")
    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    @JsonProperty("grossAmount")
    public double getGrossAmount() {
        return grossAmount;
    }

    @JsonProperty("grossAmount")
    public void setGrossAmount(double grossAmount) {
        this.grossAmount = grossAmount;
    }

    @JsonProperty("tax")
    public double getTax() {
        return tax;
    }

    @JsonProperty("tax")
    public void setTax(double tax) {
        this.tax = tax;
    }

    @JsonProperty("contractor")
    public Contractor getContractor() {
        return contractor;
    }

    @JsonProperty("contractor")
    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }
}
