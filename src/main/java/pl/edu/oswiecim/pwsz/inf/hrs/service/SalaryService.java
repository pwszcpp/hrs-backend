package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.SalaryDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Salary;

import java.text.ParseException;
import java.util.List;

@Service("salaryService")
@Transactional(readOnly = true)
public interface SalaryService {
    Salary convertToEntity(SalaryDto salaryDto) throws ParseException;

    SalaryDto convertToDTO(Salary salary);

    void saveSalary(Salary salary);

    Iterable<Salary> findAll();

    List findAllDTO();

    Salary findById(Integer id);

    void deleteSalary(Integer id);

    void updateSalary(Integer SalaryId, Salary salary) throws ParseException;
}
