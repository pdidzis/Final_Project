package lv.venta.service;

import lv.venta.model.Leave;

import java.util.List;

public interface LeaveService {

    List<Leave> getAllLeaves();

    Leave getLeaveById(Long id) throws Exception;

    void createLeave(Leave leave) throws Exception;

    void updateLeaveById(Long id, Leave leave) throws Exception;

    void deleteLeaveById(Long id) throws Exception;
}
