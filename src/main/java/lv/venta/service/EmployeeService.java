package lv.venta.service;

import lv.venta.model.Employee;

import java.util.List;

public interface EmployeeService {
    
    List<Employee> getAllEmployees();
    
    Employee getEmployeeById(Long id) throws Exception;

    void createEmployee(Employee employee) throws Exception;

    void updateEmployeeById(Long id, Employee employee) throws Exception;

    void deleteEmployeeById(Long id) throws Exception;
}
