package pl.edu.oswiecim.pwsz.inf.hrs.service;

import pl.edu.oswiecim.pwsz.inf.hrs.dto.EmployeeDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Employee;

import java.text.ParseException;
import java.util.List;

public interface EmployeeService {
    Employee convertToEntity(EmployeeDto userDto) throws ParseException;
    EmployeeDto convertToDTO(Employee employee);
    void saveEmployee(Employee employee);
    Iterable<Employee> findAll();
    List findAllDTO();
    Employee findById(Integer id);
}
