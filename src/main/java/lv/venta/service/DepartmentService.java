package lv.venta.service;

import lv.venta.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department getDepartmentById(Long id) throws Exception;

    void createDepartment(Department department) throws Exception;

    void updateDepartmentById(Long id, Department department) throws Exception;

    void deleteDepartmentById(Long id) throws Exception;
}
