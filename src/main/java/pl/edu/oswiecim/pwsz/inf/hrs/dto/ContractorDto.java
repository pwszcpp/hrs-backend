package pl.edu.oswiecim.pwsz.inf.hrs.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Address;

import javax.annotation.Generated;
import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "name",
        "tin",
       "address"
})
public class ContractorDto extends ResourceSupport {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("tin")
    private String tin;

//    @JsonCreator
//    public ContractorDto(@JsonProperty("name")String name,@JsonProperty("tin") String tin) {
//        this.name = name;
//        this.tin = tin;
//    }

    @JsonProperty("address")
    private Address address;
    @JsonProperty("id")
    public Integer getContractorId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(Integer id) {
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
    @JsonProperty("tin")
    public String getTin() {
        return tin;
    }
    @JsonProperty("tin")
    public void setTin(String tin) {
        this.tin = tin;
    }

    @JsonProperty("address")
    public Address getAddress() {
      return address;
   }
  //  @JsonProperty("address")
   public void setAddress(Address address) {
       this.address = address;
    }
}
