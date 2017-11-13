package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.EmployeeDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Employee;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Employee;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.EmployeeRepo;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Service("employeeService")
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Employee convertToEntity(EmployeeDto employeeDto) throws ParseException {
        return modelMapper.map(employeeDto, Employee.class);
    }

    @Override
    public EmployeeDto convertToDTO(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepo.save(employee);

    }

    @Override
    public Iterable<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public List findAllDTO() {
        List employeesDTOs = new ArrayList();
        Iterable<Employee> employees = employeeRepo.findAll();
        for(Employee employee : employees){
            employeesDTOs.add(convertToDTO(employee));
        }
        return employeesDTOs;
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepo.findOne(id);
    }
}
