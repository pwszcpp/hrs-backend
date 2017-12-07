package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.AddressDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Address;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.AddressRepo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service("addressService")
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(AddressServiceImpl.class);

    @Override
    public Address convertToEntity(AddressDto addressDto) throws ParseException {
        return modelMapper.map(addressDto, Address.class);
    }

    @Override
    public AddressDto convertToDTO(Address address) {
        return modelMapper.map(address, AddressDto.class);
    }

    @Override
    @Transactional
    public void saveAddress(Address address) {
        addressRepo.save(address);

    }

    @Override
    public Iterable<Address> findAll() {
        return addressRepo.findAll();
    }

    @Override
    public List findAllDTO() {
        List addressesDTOs = new ArrayList();
        Iterable<Address> addresses = addressRepo.findAll();
        for (Address address : addresses) {
            addressesDTOs.add(convertToDTO(address));
        }

        return addressesDTOs;
    }

    @Override
    public Address findById(Integer id) {
        return addressRepo.findOne(id);
    }

    @Override
    @Transactional
    public void deleteAddress(Integer id) {
        addressRepo.delete(id);
    }

    @Override
    @Transactional
    public void updateAddress(Integer id, Address address) throws ParseException {
        Address existingAd = addressRepo.findOne(id);
        existingAd.setAddressLine(address.getAddressLine());
        existingAd.setCity(address.getCity());
        existingAd.setCountry(address.getCountry());
        existingAd.setStreet(address.getStreet());
        existingAd.setPostalCode(address.getPostalCode());

        addressRepo.save(existingAd);
    }


}