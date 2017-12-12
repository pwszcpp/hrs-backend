package pl.edu.oswiecim.pwsz.inf.hrs.service;

import pl.edu.oswiecim.pwsz.inf.hrs.model.UserTraining;

public interface UserTrainingService {
    void saveUserTraining(UserTraining ut);
   // void changeEnrollStatus(Integer id, Boolean agreed);

    void deleteUserTraining(Integer userID, Integer trainingID);
}
