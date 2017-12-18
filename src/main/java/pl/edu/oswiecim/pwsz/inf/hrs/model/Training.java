package pl.edu.oswiecim.pwsz.inf.hrs.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Training", schema = "HRS_SCH")
public class Training {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "mySeqGen", sequenceName = "HRS_SCH.TRAINING_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    private Integer id;

    @Column(name = "company")
    private String company;

    @Column(name = "from_day")
    private Date startDate;

    @Column(name = "until_day")
    private Date endDate;

    @Column(name = "price")
    private Float cost;

    @Column(name = "consent")
    private Boolean consent;

    @Column(name = "place")
    private String location;

    @Column(name = "theme")
    private String theme;

    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "cancelled")
    private Boolean cancelled;

    @Column(name = "no_of_seats")
    private Integer noOfSeats;

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserTraining> userTrainings = new HashSet<>();

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

    @JsonIgnore
    public Set<UserTraining> getUserTrainings() {
        return userTrainings;
    }

    public void setUserTrainings(Set<UserTraining> trainings) {
        this.userTrainings = trainings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public Boolean getConsent() {
        return consent;
    }

    public void setConsent(Boolean consent) {
        this.consent = consent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Integer getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

}
