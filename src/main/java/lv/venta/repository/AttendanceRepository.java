package lv.venta.repository;

import lv.venta.model.Attendance;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import jakarta.transaction.Transactional;

public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Attendance a WHERE a.employee.eid = :eid")
    void deleteByEmployeeEid(Long eid);
}
