package pl.edu.oswiecim.pwsz.inf.hrs.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "name",
        "min_salary",
        "max_salary",
        "max_percent_salary_supplement",
     //   "users"
})
public class PositionDto extends ResourceSupport {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private  String name;
    @JsonProperty("min_salary")
    private double minSalary;
    @JsonProperty("max_salary")
    private double maxSalary;
    @JsonProperty("max_percent_salary_supplement")
    private double maxPercentSalarySupplement;



    @JsonProperty("id")
    public Integer getPositionId() {
        return id;
    }

    @JsonProperty("users")
    @JsonIgnoreProperties({ "positions","email","password","employmentStartDate","id","address", "role","status","passExpire", "passChangedDate","loginLastSuccess",
            "loginLastFailed","loginAttemptsFailed"})
    private List<User> users = new ArrayList();

    @JsonProperty("users")
    public List<User> getUsers() {
        return users;
    }
    @JsonProperty("users")
    public void setUsers(List<User> users) {
        this.users = users;
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
    @JsonProperty("min_salary")
    public double getMinSalary() {
        return minSalary;
    }
    @JsonProperty("min_salary")
    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }
    @JsonProperty("max_salary")
    public double getMaxSalary() {
        return maxSalary;
    }
    @JsonProperty("max_salary")
    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }
    @JsonProperty("max_percent_salary_supplement")
    public double getMaxPercentSalarySupplement() {
        return maxPercentSalarySupplement;
    }
    @JsonProperty("max_percent_salary_supplement")
    public void setMaxPercentSalarySupplement(double maxPercentSalarySupplement) {
        this.maxPercentSalarySupplement = maxPercentSalarySupplement;
    }
}
