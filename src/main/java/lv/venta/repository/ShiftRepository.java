package lv.venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import lv.venta.model.Shift;

public interface ShiftRepository extends JpaRepository<Shift, Long> {

}
