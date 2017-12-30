package pl.edu.oswiecim.pwsz.inf.hrs.service;

import pl.edu.oswiecim.pwsz.inf.hrs.model.UserTraining;

public interface UserTrainingService {
    void saveUserTraining(UserTraining ut);
    void deleteUserTraining(Integer userID, Integer trainingID) throws Exception;
    UserTraining findUserTraining(Integer userId, Integer trainingId) throws Exception;
}
