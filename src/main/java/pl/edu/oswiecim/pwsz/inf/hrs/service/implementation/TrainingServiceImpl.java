package pl.edu.oswiecim.pwsz.inf.hrs.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.TrainingDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Training;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.TrainingRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.TrainingService;

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
    public Training findById(Integer id) throws Exception {
        Training training = trainingRepo.findOne(id);
        if(training != null){
            return training;
        } else {
            throw new Exception("Training with provided id does not exist");
        }
    }

    @Transactional
    @Override
    public void updateTraining(Integer id, Training training) throws Exception {

        if (trainingRepo.findOne(id) != null) {
            Training existingTrai = trainingRepo.findOne(id);
            existingTrai.setTheme(training.getTheme());
            existingTrai.setLocation(training.getLocation());
            existingTrai.setAuthorId(training.getAuthorId());
            existingTrai.setConsent(training.getConsent());
            existingTrai.setStartDate(training.getStartDate());
            existingTrai.setEndDate(training.getEndDate());
            existingTrai.setCompany(training.getCompany());
            existingTrai.setNoOfSeats(training.getNoOfSeats());
            existingTrai.setCancelled(training.getCancelled());
            existingTrai.setCost(training.getCost());

            trainingRepo.save(existingTrai);
        } else {
            throw new Exception("Training with provided id does not exist");
        }
    }

    @Override
    @Transactional
    public void deleteTraining(Integer id) throws Exception {
        if(trainingRepo.findOne(id) != null){
            trainingRepo.delete(id);
        } else {
            throw new Exception("Training with provided id does not exist");
        }

    }

    @Override
    public Page<Training> listAllByPage(Pageable pageable) {
        return trainingRepo.findAll(pageable);
    }
}
