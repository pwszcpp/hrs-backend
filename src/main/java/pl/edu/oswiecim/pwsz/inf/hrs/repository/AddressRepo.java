package pl.edu.oswiecim.pwsz.inf.hrs.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Address;

@Repository("addressRepository")
public interface AddressRepo extends CrudRepository<Address, Integer> {

}
