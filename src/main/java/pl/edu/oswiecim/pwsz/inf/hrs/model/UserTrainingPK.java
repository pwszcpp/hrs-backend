package pl.edu.oswiecim.pwsz.inf.hrs.model;


import java.io.Serializable;

public class UserTrainingPK implements Serializable{

    private User user;
    private Training training;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
