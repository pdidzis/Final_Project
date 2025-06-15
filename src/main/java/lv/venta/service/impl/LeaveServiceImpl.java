package lv.venta.service.impl;

import lv.venta.model.Leave;
import lv.venta.repository.LeaveRepository;
import lv.venta.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepo;

    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepo.findAll();
    }

    @Override
    public Leave getLeaveById(Long id) throws Exception {
        return leaveRepo.findById(id)
                .orElseThrow(() -> new Exception("Leave not found with id: " + id));
    }

    @Override
    public void createLeave(Leave leave) {
        leaveRepo.save(leave);
    }

    @Override
    public void updateLeaveById(Long id, Leave updatedLeave) throws Exception {
        Leave leave = getLeaveById(id);
        leave.setEmployee(updatedLeave.getEmployee());
        leave.setStartDate(updatedLeave.getStartDate());
        leave.setEndDate(updatedLeave.getEndDate());
        leaveRepo.save(leave);
    }

    @Override
    public void deleteLeaveById(Long id) {
        leaveRepo.deleteById(id);
    }
}
