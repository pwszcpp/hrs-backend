package pl.edu.oswiecim.pwsz.inf.hrs.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.PositionDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Position;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.PositionRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.PositionService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service("positionService")
@Transactional(readOnly = true)
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepo positionRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Position convertToEntity(PositionDto positionDto) throws ParseException {
        return modelMapper.map(positionDto, Position.class);
    }

    @Override
    public PositionDto convertToDTO(Position position) {
        return modelMapper.map(position, PositionDto.class);
    }

    @Override
    @Transactional
    public void savePosition(Position position) {
        positionRepo.save(position);
    }

    @Override
    public Iterable<Position> findAll() {
        return positionRepo.findAll();
    }

    @Override
    public List findAllDTO() {
        List positionDTOs = new ArrayList();
        Iterable<Position> positions = positionRepo.findAll();
        for (Position position : positions) {
            positionDTOs.add(convertToDTO(position));
        }

        return positionDTOs;
    }

    @Override
    public Position findById(Integer id) {
        return positionRepo.findOne(id);
    }

    @Override
    @Transactional
    public void deletePosition(Integer id) {
        positionRepo.delete(id);
    }

    @Override
    @Transactional
    public void updatePosition(Integer positionId, Position position) throws ParseException {
        Position existingPosition = positionRepo.findOne(positionId);

        existingPosition.setName(position.getName());
        existingPosition.setMaxPercentSalarySupplement(position.getMaxPercentSalarySupplement());
        existingPosition.setMaxSalary(position.getMaxSalary());
        existingPosition.setMinSalary(position.getMinSalary());

        positionRepo.save(existingPosition);
    }


}
