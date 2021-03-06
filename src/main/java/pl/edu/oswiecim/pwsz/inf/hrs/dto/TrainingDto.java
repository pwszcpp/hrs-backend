package pl.edu.oswiecim.pwsz.inf.hrs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.ResourceSupport;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;

import javax.annotation.Generated;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "name",
        "owner",
        "startDate",
        "endDate",
        "cost",
        "permission",
        "location",
        "enrolledUsers",
        "users"
})
public class TrainingDto extends ResourceSupport {


    @JsonProperty("name")
    private String name;
    @JsonProperty("owner")
    private String owner;
    @JsonProperty("startDate")
    private Date startDate;
    @JsonProperty("endDate")
    private Date endDate;
    @JsonProperty("cost")
    private Float cost;
    @JsonProperty("permission")
    private Boolean permission;
    @JsonProperty("location")
    private String location;
    @JsonProperty("id")
    private String id;
    @JsonProperty("enrolledUsers")
    private Set<User> enrolledUsers = new HashSet<>();
    @JsonProperty("users")
    private Set<User> users = new HashSet<>();


    @JsonProperty("name")
    public String getName() {
        return name;
    }


    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("owner")
    public String getOwner() {
        return owner;
    }

    @JsonProperty("owner")
    public void setOwner(String owner) {
        this.owner = owner;
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

    @JsonProperty("permission")
    public Boolean getPermission() {
        return permission;
    }

    @JsonProperty("permission")
    public void setPermission(Boolean permission) {
        this.permission = permission;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("id")
    public String getTrainingId() {
        return id;
    }

    @JsonProperty("users")
    public Set<User> getUsers() {
        return users;
    }
    @JsonProperty("users")
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @JsonProperty("enrolledUsers")
    public Set<User> getEnrolledUsers() {
        return enrolledUsers;
    }
    @JsonProperty("enrolledUsers")
    public void setEnrolledUsers(Set<User> enrolledUsers) {
        this.enrolledUsers = enrolledUsers;
    }
}
