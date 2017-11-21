package pl.edu.oswiecim.pwsz.inf.hrs.service;

import pl.edu.oswiecim.pwsz.inf.hrs.dto.TrainingDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Training;

import java.text.ParseException;
import java.util.List;

public interface TrainingService {
    Training convertToEntity(TrainingDto trainingDto) throws ParseException;
    TrainingDto convertToDTO(Training training);
    void saveTraining(Training training);
    Iterable<Training> findAll();
    List findAllDTO();
    Training findById(Integer id);
    void updateTraining(Integer id,Training training);
}
