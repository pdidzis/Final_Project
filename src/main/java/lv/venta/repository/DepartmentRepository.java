package lv.venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import lv.venta.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
