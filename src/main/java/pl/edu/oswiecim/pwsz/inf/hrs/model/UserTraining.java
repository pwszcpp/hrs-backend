package pl.edu.oswiecim.pwsz.inf.hrs.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@IdClass(UserTrainingPK.class)
@Table(name = "User_Training", schema = "HRS_SCH")
public class UserTraining implements Serializable{

    private User user;
    private Training training;

//    private String note;

    @Column(name = "date_of_sign")
    private Date SignDate;

    private Boolean cancelled;
    private Boolean agreed;

    @Id
    @ManyToOne
    @JoinColumn(name = "Users_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "Training_ID")
    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

//    public String getNote() {
//        return note;
//    }

//    public void setNote(String note) {
//        this.note = note;
//    }

    @Column(name = "date_of_sign")
    public Date getSignDate() {
        return SignDate;
    }

    @Column(name = "date_of_sign")
    public void setSignDate(Date signDate) {
        SignDate = signDate;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Boolean getAgreed() {
        return agreed;
    }

    public void setAgreed(Boolean agreed) {
        this.agreed = agreed;
    }
}
