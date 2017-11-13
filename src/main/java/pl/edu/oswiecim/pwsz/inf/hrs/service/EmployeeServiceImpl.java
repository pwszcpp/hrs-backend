package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER =
            LoggerFactory.getLogger(EmployeeServiceImpl.class);

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
    public void deleteEmployee(Integer id) {
        employeeRepo.delete(id);
    }

    @Override
    public void updateEmployee(Integer id, Employee employee) {
        Employee existingEmp = employeeRepo.findOne(id);
        existingEmp.setFirstName(employee.getFirstName());
        existingEmp.setLastName(employee.getLastName());
        existingEmp.setJob(employee.getJob());
        existingEmp.setSex(employee.getSex());
        existingEmp.setSalary(employee.getSalary());

        employeeRepo.save(existingEmp);
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
