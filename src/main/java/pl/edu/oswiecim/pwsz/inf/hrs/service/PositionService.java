package pl.edu.oswiecim.pwsz.inf.hrs.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.PositionDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Position;

import java.text.ParseException;
import java.util.List;

@Service("positionService")
@Transactional(readOnly = true)
public interface PositionService {

    Position convertToEntity(PositionDto positionDto) throws ParseException;

    PositionDto convertToDTO(Position position);

    void savePosition(Position position);

    Iterable<Position> findAll();

    List findAllDTO();

    Position findById(Integer id);

    void deletePosition(Integer id);

    void updatePosition(Integer positionId, Position position) throws ParseException;



    //String[] divideJson(String jsonInString);
}
