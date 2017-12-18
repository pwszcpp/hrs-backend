package pl.edu.oswiecim.pwsz.inf.hrs.service.implementation;

import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.SalaryDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Salary;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.SalaryRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.UserRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.SalaryService;

import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Service("SalaryService")
@Transactional(readOnly = true)
public class SalaryServiceImpl implements SalaryService{

    @Autowired
    private SalaryRepo salaryRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Salary convertToEntity(SalaryDto salaryDto) throws ParseException {
        return modelMapper.map(salaryDto, Salary.class);
    }

    @Override
    public SalaryDto convertToDTO(Salary salary) {
        return modelMapper.map(salary, SalaryDto.class);
    }

    @Transactional
    @Override
    public void saveSalary(Salary salary) {
        salaryRepo.save(salary);
    }

    @Override
    public Iterable<Salary> findAll() {
        return salaryRepo.findAll();
    }

    @Override
    public List findAllDTO() {
        List salaryDTOs = new ArrayList();
        Iterable<Salary> salaries = salaryRepo.findAll();
        for(Salary salary : salaries){
            salaryDTOs.add(convertToDTO(salary));
        }
        return salaryDTOs;
    }

    @Override
    public Salary findById(Integer id) {
        return salaryRepo.findOne(id);
    }

    @Override
    @Transactional
    public void deleteSalary(Integer id) {

        salaryRepo.delete(id);
    }

    @Override
    @Transactional
    public void updateSalary(Integer SalaryId, Salary salary, Integer userId) throws ParseException {

        Salary existingSalary = salaryRepo.findOne(SalaryId);
        existingSalary.setBaseSalary(salary.getBaseSalary());
        existingSalary.setEmploymentArrangement(salary.getEmploymentArrangement());
        existingSalary.setEmploymentStatus(salary.getEmploymentStatus());
        existingSalary.setSalarySupplement(salary.getSalarySupplement());
        existingSalary.setSeniority(salary.getSeniority());
        existingSalary.setUser(userRepo.findOne(userId));

        salaryRepo.save(existingSalary);
    }
    @Override
    public String[] divideJson(String jsonInString) {
        JSONObject jsonObject = new JSONObject(jsonInString);
        JSONObject jsonInvoice = new JSONObject();
        try {
            Integer userId = jsonObject.getInt("users_id");

            jsonInvoice.put("employment_arrangement", jsonObject.get("employment_arrangement"));
            jsonInvoice.put("base_salary", jsonObject.get("base_salary"));
            jsonInvoice.put("seniority", jsonObject.get("seniority"));
            jsonInvoice.put("salary_supplement", jsonObject.get("salary_supplement"));
            jsonInvoice.put("employment_status", jsonObject.get("employment_status"));
            String invoiceReader = jsonInvoice.toString();
            return new String[]{userId.toString(), invoiceReader};

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new String[]{""};
    }
}
