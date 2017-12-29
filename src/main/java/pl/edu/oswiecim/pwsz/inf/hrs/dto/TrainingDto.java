package pl.edu.oswiecim.pwsz.inf.hrs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.ResourceSupport;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Training;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;
import pl.edu.oswiecim.pwsz.inf.hrs.model.UserTraining;
import pl.edu.oswiecim.pwsz.inf.hrs.service.TrainingService;

import javax.annotation.Generated;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "company",
        "startDate",
        "endDate",
        "cost",
        "consent",
        "location",
        "theme",
//        "author_id",
        "cancelled",
        "no_of_seats",
})
public class TrainingDto extends ResourceSupport {

    @Autowired
    TrainingService trainingService;

    public TrainingDto(){

    }

    @JsonProperty("name")
    private String name;
    @JsonProperty("company")
    private String company;
    @JsonProperty("startDate")
    private Date startDate;
    @JsonProperty("endDate")
    private Date endDate;
    @JsonProperty("cost")
    private Float cost;
    @JsonProperty("consent")
    private Boolean consent;
    @JsonProperty("location")
    private String location;
    @JsonProperty("id")
    private String id;
    @JsonProperty("theme")
    private String theme;

//    @JsonProperty("author_id")
//    private Integer authorId;

    @JsonProperty("cancelled")
    private Boolean cancelled;
    @JsonProperty("no_of_seats")
    private Integer noOfSeats;

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("id")
    public String getTrainingId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("company")
    public String getCompany() {
        return company;
    }

    @JsonProperty("company")
    public void setCompany(String company) {
        this.company = company;
    }

    @JsonProperty("startDate")
    public Date getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("endDate")
    public Date getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("cost")
    public Float getCost() {
        return cost;
    }

    @JsonProperty("cost")
    public void setCost(Float cost) {
        this.cost = cost;
    }

    @JsonProperty("consent")
    public Boolean getConsent() {
        return consent;
    }

    @JsonProperty("consent")
    public void setConsent(Boolean consent) {
        this.consent = consent;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("theme")
    public String getTheme() {
        return theme;
    }

    @JsonProperty("theme")
    public void setTheme(String theme) {
        this.theme = theme;
    }

//    @JsonProperty("author_id")
//    public Integer getAuthorId() {
//        return authorId;
//    }
//
//    @JsonProperty("author_id")
//    public void setAuthorId(Integer authorId) {
//        this.authorId = authorId;
//    }

    @JsonProperty("cancelled")
    public Boolean getCancelled() {
        return cancelled;
    }

    @JsonProperty("cancelled")
    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    @JsonProperty("no_of_seats")
    public Integer getNoOfSeats() {
        return noOfSeats;
    }

    @JsonProperty("no_of_seats")
    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = noOfSeats;
    }
}
