package pl.edu.oswiecim.pwsz.inf.hrs.service.implementation;

import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.dto.LeaveDto;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Leave;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.LeaveRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.UserRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.LeaveService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service("leaveService")
@Transactional(readOnly = true)
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepo leaveRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Leave convertToEntity(LeaveDto leaveDto) throws ParseException {
        return modelMapper.map(leaveDto, Leave.class);
    }

    @Override
    public LeaveDto convertToDTO(Leave leave) {
        return modelMapper.map(leave, LeaveDto.class);
    }

    @Override
    @Transactional
    public void saveLeave(Leave leave) {
        leaveRepo.save(leave);

    }

    @Override
    public Iterable<Leave> findAll() {
        return leaveRepo.findAll();
    }

    @Override
    public List findAllDTO() {
        List leaveDTOs = new ArrayList();
        Iterable<Leave> leaves = leaveRepo.findAll();
        for(Leave leave : leaves) {
            leaveDTOs.add(convertToDTO(leave));
        }
        return leaveDTOs;
    }

    @Override
    public Leave findById(Integer id) {
        return leaveRepo.findOne(id);
    }

    @Override
    @Transactional
    public void deleteLeave(Integer id) {
        leaveRepo.delete(id);
    }

    @Override
    @Transactional
    public void updateLeave(Integer leaveId, Leave leave,Integer userId) throws ParseException {
        Leave existingLeave = leaveRepo.findOne(leaveId);

        existingLeave.setAgreed(leave.getAgreed());
        existingLeave.setCreateDate(leave.getEndDate());
        existingLeave.setDaysUsed(leave.getDaysUsed());
        existingLeave.setDisagreeReason(leave.getDisagreeReason());
        existingLeave.setEndDate(leave.getEndDate());
        existingLeave.setLeaveDimension(leave.getLeaveDimension());
        existingLeave.setOverdueLeave(leave.getOverdueLeave());
        existingLeave.setStartDate(leave.getStartDate());
        existingLeave.setUser(userRepo.findOne(userId));

        leaveRepo.save(existingLeave);

    }

    @Override
    public String[] divideJson(String jsonInString) {
        JSONObject jsonObject = new JSONObject(jsonInString);
        JSONObject jsonInvoice = new JSONObject();
        try {
            Integer userId = jsonObject.getInt("users_id");

            jsonInvoice.put("create_date", jsonObject.get("create_date"));
            jsonInvoice.put("start_date", jsonObject.get("start_date"));
            jsonInvoice.put("end_date", jsonObject.get("end_date"));
            jsonInvoice.put("leave_dimension", jsonObject.get("leave_dimension"));
            jsonInvoice.put("overdue_leave", jsonObject.get("overdue_leave"));
            jsonInvoice.put("days_used", jsonObject.get("days_used"));
            jsonInvoice.put("agreed", jsonObject.get("agreed"));
            jsonInvoice.put("disagree_reason", jsonObject.get("disagree_reason"));
            String invoiceReader = jsonInvoice.toString();
            return new String[]{userId.toString(), invoiceReader};

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new String[]{""};
    }
}
