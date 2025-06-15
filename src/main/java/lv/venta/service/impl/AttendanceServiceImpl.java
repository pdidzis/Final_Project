package lv.venta.service.impl;

import lv.venta.model.Attendance;
import lv.venta.repository.AttendanceRepository;
import lv.venta.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Override
    public List<Attendance> getAllAttendances() {
        return (List<Attendance>) attendanceRepo.findAll();
    }

    @Override
    public Attendance getAttendanceById(Long id) throws Exception {
        return attendanceRepo.findById(id)
                .orElseThrow(() -> new Exception("Attendance not found with id: " + id));
    }

    @Override
    public void createAttendance(Attendance attendance) {
        attendanceRepo.save(attendance);
    }

    @Override
    public void updateAttendanceById(Long id, Attendance updatedAttendance) throws Exception {
        Attendance attendance = getAttendanceById(id);
        attendance.setEmployee(updatedAttendance.getEmployee());
        attendance.setDate(updatedAttendance.getDate());
        attendanceRepo.save(attendance);
    }

    @Override
    public void deleteAttendanceById(Long id) {
        attendanceRepo.deleteById(id);
    }
}
