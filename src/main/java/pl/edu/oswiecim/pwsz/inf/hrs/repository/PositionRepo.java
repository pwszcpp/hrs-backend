package pl.edu.oswiecim.pwsz.inf.hrs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Position;

@Repository("positionRepository")
public interface PositionRepo extends CrudRepository<Position, Integer>,PagingAndSortingRepository<Position,Integer> {
}
