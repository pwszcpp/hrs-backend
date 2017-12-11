package pl.edu.oswiecim.pwsz.inf.hrs.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Training;
import pl.edu.oswiecim.pwsz.inf.hrs.model.UserTraining;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.TrainingRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.TrainingService;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserTrainingService;

import javax.persistence.EntityManager;
import java.util.Set;

@Service("userTrainingService")
public class UserTrainingServiceImpl implements UserTrainingService{

    @Autowired
    EntityManager em;

    @Autowired
    TrainingService trainingService;

    @Override
    @Transactional
    public void saveUserTraining(UserTraining ut) {
        em.persist(ut);
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
