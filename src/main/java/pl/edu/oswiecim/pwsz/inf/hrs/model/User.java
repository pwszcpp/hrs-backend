package pl.edu.oswiecim.pwsz.inf.hrs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
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
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserTraining> userTrainings = new HashSet<>();

    public enum Status {
        ENABLED,
        DISABLED
    };

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "pass_expire")
    private Date passExpire;

    @Column(name = "pass_changed_date")
    private Date passChangedDate;

    @Column(name = "login_last_success")
    private Date loginLastSuccess;

    @Column(name = "login_last_failed")
    private Date loginLastFailed;

    @Column(name = "login_attempts_failed")
    private Integer loginAttemptsFailed;

    public User() {
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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
    public Set<UserTraining> getUserTrainings() {
        return userTrainings;
    }

    public void setUserTrainings(Set<UserTraining> trainings) {
        this.userTrainings = trainings;
    }

}
