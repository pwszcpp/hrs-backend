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
        "users_id",
        "employment_arrangement",
        "base_salary",
        "seniority",
        "salary_supplement",
        "employment_status"
})

public class SalaryDto extends ResourceSupport{

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("users_id")
    private Integer userId;

    @JsonProperty("employment_arrangement")
    private String employmentArrangement;

    @JsonProperty("base_salary")
    private Double baseSalary;

    @JsonProperty("seniority")
    private Integer seniority;

    @JsonProperty("salary_supplement")
    private Double salarySupplement;

    @JsonProperty("employment_status")
    private String employmentStatus;
    @JsonProperty("id")
    public Integer getSalaryId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("users_id")
    public Integer getUserId() {
        return userId;
    }
    @JsonProperty("users_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("employment_arrangement")
    public String getEmploymentArrangement() {
        return employmentArrangement;
    }
    @JsonProperty("employment_arrangement")
    public void setEmploymentArrangement(String employmentArrangement) {
        this.employmentArrangement = employmentArrangement;
    }
    @JsonProperty("base_salary")
    public Double getBaseSalary() {
        return baseSalary;
    }
    @JsonProperty("base_salary")
    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }
    @JsonProperty("seniority")
    public Integer getSeniority() {
        return seniority;
    }
    @JsonProperty("seniority")
    public void setSeniority(Integer seniority) {
        this.seniority = seniority;
    }
    @JsonProperty("salary_supplement")
    public Double getSalarySupplement() {
        return salarySupplement;
    }
    @JsonProperty("salary_supplement")
    public void setSalarySupplement(Double salarySupplement) {
        this.salarySupplement = salarySupplement;
    }
    @JsonProperty("employment_status")
    public String getEmploymentStatus() {
        return employmentStatus;
    }
    @JsonProperty("employment_status")
    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }
}
