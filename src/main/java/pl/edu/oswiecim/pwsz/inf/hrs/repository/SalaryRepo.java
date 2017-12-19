package pl.edu.oswiecim.pwsz.inf.hrs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Salary;

@Repository("salaryRepository")
public interface SalaryRepo extends CrudRepository<Salary, Integer>,PagingAndSortingRepository<Salary,Integer> {

}
