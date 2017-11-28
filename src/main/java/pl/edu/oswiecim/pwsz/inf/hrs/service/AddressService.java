package pl.edu.oswiecim.pwsz.inf.hrs.service;

import pl.edu.oswiecim.pwsz.inf.hrs.dto.AddressDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Address;

import java.text.ParseException;
import java.util.List;

public interface AddressService {
    Address convertToEntity(AddressDto userDto) throws ParseException;
    AddressDto convertToDTO(Address address);
    void saveAddress(Address address);
    Iterable<Address> findAll();
    List findAllDTO();
    Address findById(Integer id);
    void deleteAddress(Integer id);
    void updateAddress(Integer id, Address address) throws ParseException;
}
