package pl.edu.oswiecim.pwsz.inf.hrs.service;

import pl.edu.oswiecim.pwsz.inf.hrs.dto.TrainingDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Training;

import java.text.ParseException;

public interface TrainingService {
    Training convertToEntity(TrainingDto trainingDto) throws ParseException;
    void saveTraining(Training training);
    Iterable<Training> findAll();
    Training findTrainingByName(String name);
}
