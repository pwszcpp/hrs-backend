package pl.edu.oswiecim.pwsz.inf.hrs.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Training;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;
import pl.edu.oswiecim.pwsz.inf.hrs.model.UserTraining;
import pl.edu.oswiecim.pwsz.inf.hrs.model.UserTrainingPK;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.TrainingRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.TrainingService;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserService;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserTrainingService;

import javax.persistence.EntityManager;
import java.util.Set;

@Service("userTrainingService")
public class UserTrainingServiceImpl implements UserTrainingService{

    @Autowired
    EntityManager em;

    @Autowired
    TrainingService trainingService;

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public void saveUserTraining(UserTraining ut) {
        em.persist(ut);
    }

    @Override
    @Transactional
    public void deleteUserTraining(Integer userID, Integer trainingID) throws Exception {
        Training training = trainingService.findById(trainingID);
        User user = userService.findById(userID);

        UserTrainingPK userTrainingPK = new UserTrainingPK();
        userTrainingPK.setUser(user);
        userTrainingPK.setTraining(training);

        UserTraining userTraining = em.find(UserTraining.class, userTrainingPK);
        em.remove(userTraining);
    }

    @Override
    public UserTraining findUserTraining(Integer userId, Integer trainingId) throws Exception {

        UserTrainingPK userTrainingPK = new UserTrainingPK();
        userTrainingPK.setUser(userService.findById(userId));
        userTrainingPK.setTraining(trainingService.findById(trainingId));

        return em.find(UserTraining.class, userTrainingPK);

    }
}
