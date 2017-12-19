package pl.edu.oswiecim.pwsz.inf.hrs.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.LeaveDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Leave;

import java.text.ParseException;
import java.util.List;

@Service("leaveService")
@Transactional(readOnly = true)
public interface LeaveService {
    Leave convertToEntity(LeaveDto leaveDto) throws ParseException;

    LeaveDto convertToDTO(Leave leave);

    void saveLeave(Leave leave);

    Iterable<Leave> findAll();

    List findAllDTO();

    Leave findById(Integer id);

    void deleteLeave(Integer id);

    void updateLeave(Integer leaveId, Leave leave, Integer userId) throws ParseException;
    String[] divideJson(String jsonInString);

    Page<Leave> listAllByPage(Pageable pageable);
}
