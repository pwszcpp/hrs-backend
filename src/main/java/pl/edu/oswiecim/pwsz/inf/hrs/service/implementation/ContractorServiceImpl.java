package pl.edu.oswiecim.pwsz.inf.hrs.service.implementation;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.ContractorDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Contractor;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.ContractorRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.ContractorService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service("contractorService")
@Transactional(readOnly = true)
public class ContractorServiceImpl implements ContractorService {

    @Autowired
    private ContractorRepo contractorRepo;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ContractorServiceImpl.class);

    @Override
    public Contractor convertToEntity(ContractorDto contractorDto) throws ParseException {
        return modelMapper.map(contractorDto, Contractor.class);
    }

    @Override
    public ContractorDto convertToDTO(Contractor contractor) {
        return modelMapper.map(contractor, ContractorDto.class);
    }

    @Override
    @Transactional
    public void saveContractor(Contractor contractor) {
        contractorRepo.save(contractor);
    }

    @Override
    public Iterable<Contractor> findAll() {
        return contractorRepo.findAll();
    }

    @Override
    public List findAllDTO() {
        List contractorsDTOs = new ArrayList();
        Iterable<Contractor> contractors = contractorRepo.findAll();
        for (Contractor contractor : contractors) {
            contractorsDTOs.add(convertToDTO(contractor));
        }
        return contractorsDTOs;
    }

    @Override
    public Contractor findById(Integer id) {
        return contractorRepo.findOne(id);
    }

    @Override
    @Transactional
    public void deleteContractor(Integer id) {
        contractorRepo.delete(id);
    }

    @Override
    @Transactional
    public void updateContractor(Integer contractor_id, Contractor contractor) throws ParseException {
        Contractor existingCont = contractorRepo.findOne(contractor_id);
        existingCont.setName(contractor.getName());
        existingCont.setNip(contractor.getNip());
        existingCont.setAddress(contractor.getAddress());
        existingCont.setRegon(contractor.getRegon());
        existingCont.setAccountNumber(contractor.getAccountNumber());
        existingCont.setActiveTaxpayerVATTax(contractor.getActiveTaxpayerVATTax());
        existingCont.setPaymentForm(contractor.getPaymentForm());
        contractorRepo.save(existingCont);
    }

    @Override
    public String[] divideJson(String jsonInString) {

        JSONObject jsonObject = new JSONObject(jsonInString);
        JSONObject jsonAddressUp = new JSONObject();
        JSONObject jsonContractorUp = new JSONObject();

        jsonContractorUp.put("name", jsonObject.get("name"));
        jsonContractorUp.put("tin", jsonObject.get("tin"));
        jsonAddressUp.put("city", jsonObject.get("city"));
        jsonAddressUp.put("street", jsonObject.get("street"));
        jsonAddressUp.put("postalCode", jsonObject.get("postalCode"));
        jsonAddressUp.put("country", jsonObject.get("country"));
        jsonAddressUp.put("addressLine", jsonObject.get("addressLine"));

        String contractorReader = jsonContractorUp.toString();
        String addressReader = jsonAddressUp.toString();
        return new String[]{contractorReader, addressReader};
    }
    @Override
    public Page<Contractor> listAllByPage(Pageable pageable) {
        return contractorRepo.findAll(pageable);
    }
}