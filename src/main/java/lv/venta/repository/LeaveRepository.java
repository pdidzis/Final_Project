package lv.venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import lv.venta.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

}
