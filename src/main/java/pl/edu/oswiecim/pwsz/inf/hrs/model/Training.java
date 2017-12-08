package pl.edu.oswiecim.pwsz.inf.hrs.model;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Training")
public class Training {

    @Id
    @Column(name = "training_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "training_name")
    private String name;

    @Column(name = "owner")
    private String owner;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "cost")
    private Float cost;

    @Column(name = "permission")
    private Boolean permission;

    @Column(name = "location")
    private String location;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "training_user", joinColumns = @JoinColumn(name = "training_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "enrolled_user", joinColumns = @JoinColumn(name = "training_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private Set<User> enrolledUsers = new HashSet<>();

//    public Set<User> getEnrolledUsers() {
//        return enrolledUsers;
//    }
//
//    public void setEnrolledUsers(Set<User> enrolledUsers) {
//        this.enrolledUsers = enrolledUsers;
//    }

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Boolean getPermission() {
        return permission;
    }

    public void setPermission(Boolean permission) {
        this.permission = permission;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
