package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.TrainingDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Training;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.TrainingRepo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service("trainingService")
@Transactional(readOnly = true)
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TrainingRepo trainingRepo;

    @Override
    public Training convertToEntity(TrainingDto trainingDto) throws ParseException {
        return modelMapper.map(trainingDto, Training.class);
    }

    @Override
    public TrainingDto convertToDTO(Training training) {
        return modelMapper.map(training, TrainingDto.class);
    }

    @Transactional
    @Override
    public void saveTraining(Training training) {
        trainingRepo.save(training);
    }

    @Override
    public Iterable<Training> findAll() {
        return trainingRepo.findAll();
    }

    @Override
    public List findAllDTO() {
        List trainingsDTOs = new ArrayList();
        Iterable<Training> trainings = trainingRepo.findAll();
        for (Training training : trainings) {
            trainingsDTOs.add(convertToDTO(training));
        }
        return trainingsDTOs;
    }

    @Override
    public Training findById(Integer id) {
        return trainingRepo.findOne(id);
    }

    @Transactional
    @Override
    public void updateTraining(Integer id, Training training) {

        Training existingTrai = trainingRepo.findOne(id);
        existingTrai.setName(training.getName());
        existingTrai.setLocation(training.getLocation());
        existingTrai.setOwner(training.getOwner());
        existingTrai.setPermission(training.getPermission());
        existingTrai.setStartDate(training.getStartDate());
        existingTrai.setEndDate(training.getEndDate());

        trainingRepo.save(existingTrai);
    }
}
