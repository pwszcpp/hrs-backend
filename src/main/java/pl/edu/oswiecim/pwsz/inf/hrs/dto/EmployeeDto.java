package pl.edu.oswiecim.pwsz.inf.hrs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;

import javax.annotation.Generated;
import java.sql.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "firstName",
        "lastName",
        "sex",
        "job",
        "hireDate",
        "salary"
})
public class EmployeeDto extends ResourceSupport {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("sex")
    private String sex;

    @JsonProperty("job")
    private String job;

    @JsonProperty("hireDate")
    private Date hireDate;

    @JsonProperty("salary")
    private double salary;

    @JsonProperty("id")
    public Integer getEmployeeId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("sex")
    public String getSex() {
        return sex;
    }

    @JsonProperty("sex")
    public void setSex(String sex) {
        this.sex = sex;
    }

    @JsonProperty("job")
    public String getJob() {
        return job;
    }

    @JsonProperty("job")
    public void setJob(String job) {
        this.job = job;
    }

    @JsonProperty("hireDate")
    public Date getHireDate() {
        return hireDate;
    }

    @JsonProperty("hireDate")
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @JsonProperty("salary")
    public double getSalary() {
        return salary;
    }

    @JsonProperty("salary")
    public void setSalary(double salary) {
        this.salary = salary;
    }


}
