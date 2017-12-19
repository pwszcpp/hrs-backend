package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.ContractorDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Contractor;

import java.text.ParseException;
import java.util.List;

public interface ContractorService {
    Contractor convertToEntity(ContractorDto contractorDto) throws ParseException;
    ContractorDto convertToDTO(Contractor contractor);
    void saveContractor(Contractor contractor);
    Iterable<Contractor> findAll();
    List findAllDTO();
    Contractor findById(Integer id);
    void deleteContractor(Integer id);
    void updateContractor(Integer id, Contractor Contractor) throws ParseException;

    String[] divideJson(String jsonInString);

    Page<Contractor> listAllByPage(Pageable pageable);
}