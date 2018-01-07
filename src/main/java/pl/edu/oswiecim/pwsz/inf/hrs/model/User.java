package pl.edu.oswiecim.pwsz.inf.hrs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users", schema = "HRS_SCH")
public class User {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "mySeqGen", sequenceName = "HRS_SCH.USERS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "`e-mail`")
    private String email;

    @Column(name = "pass")
    private String password;

    @Column(name = "role")
    private Integer role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserTraining> userTrainings = new HashSet<>();

    @Column(name = "status")
    //@Enumerated(EnumType.STRING)
    private Boolean status;

    @Column(name = "pass_Expire")
    private Date passExpire ;

    @Column(name = "pass_changed_date")
    private Date passChangedDate;

    @Column(name = "login_last_success")
    private Date loginLastSuccess;

    @Column(name = "login_last_failed")
    private Date loginLastFailed;

    @Column(name = "login_attempts_failed")
    private Integer loginAttemptsFailed;

    @Column(name = "address")
    private String address;

    @Column(name = "employment_start_date")
    private Date employmentStartDate;


    @Column(name = "forename")
    private String forename;

    @Column(name = "surname")
    private String surname;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "HRS_SCH.User_Position", joinColumns = @JoinColumn(name = "Users_Id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Position_Id", referencedColumnName = "id"))
    private Set<Position> positions = new HashSet<>();


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEmploymentStartDate() {
        return employmentStartDate;
    }

    public void setEmploymentStartDate(Date employmentStartDate) {
        this.employmentStartDate = employmentStartDate;
    }



//    public User() {
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getPassExpire() {
        return passExpire;
    }

    public void setPassExpire(Date passExpire) {
        this.passExpire = passExpire;
    }

    public Date getPassChangedDate() {
        return passChangedDate;
    }

    public void setPassChangedDate(Date passChangedDate) {
        this.passChangedDate = passChangedDate;
    }

    public Date getLoginLastSuccess() {
        return loginLastSuccess;
    }

    public void setLoginLastSuccess(Date loginLastSuccess) {
        this.loginLastSuccess = loginLastSuccess;
    }

    public Date getLoginLastFailed() {
        return loginLastFailed;
    }

    public void setLoginLastFailed(Date loginLastFailed) {
        this.loginLastFailed = loginLastFailed;
    }

    public Integer getLoginAttemptsFailed() {
        return loginAttemptsFailed;
    }

    public void setLoginAttemptsFailed(Integer loginAttemptsFailed) {
        this.loginAttemptsFailed = loginAttemptsFailed;
    }

    @JsonIgnore
    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }

    @JsonIgnore
    public Set<UserTraining> getUserTrainings() {
        return userTrainings;
    }

    public void setUserTrainings(Set<UserTraining> trainings) {
        this.userTrainings = trainings;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
