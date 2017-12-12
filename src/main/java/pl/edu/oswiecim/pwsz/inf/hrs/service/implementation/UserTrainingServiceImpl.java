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
    public void deleteUserTraining(Integer userID, Integer trainingID) {
        Training training = trainingService.findById(trainingID);
        User user = userService.findById(userID);

//        UserTraining userTraining = new UserTraining();
//
//        userTraining.setUser(user);
//        userTraining.setTraining(training);

        UserTrainingPK userTrainingPK = new UserTrainingPK();
        userTrainingPK.setUser(user);
        userTrainingPK.setTraining(training);

        UserTraining userTraining = em.find(UserTraining.class, userTrainingPK);
        em.remove(userTraining);
    }

//    @Transactional
//    @Override
//    public void changeEnrollStatus(Integer id, Boolean agreed) {
//
//        Training training = trainingService.findById(id);
//        Set<UserTraining> userTrainings = training.getUserTrainings();
//
//        UserTraining existingUT = em.find(UserTraining.class, training);
//        if(existingUT != null){
//
//            existingUT.setAgreed(agreed);
//
//            em.persist(existingUT);
//        }
//
//    }
}
