package lv.venta.service;

import lv.venta.model.Attendance;

import java.util.List;

public interface AttendanceService {

    List<Attendance> getAllAttendances();

    Attendance getAttendanceById(Long id) throws Exception;

    void createAttendance(Attendance attendance) throws Exception;

    void updateAttendanceById(Long id, Attendance attendance) throws Exception;

    void deleteAttendanceById(Long id) throws Exception;
}
