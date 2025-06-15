package lv.venta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.venta.service.EmployeeService;
import lv.venta.model.Employee;
import lv.venta.repository.EmployeeRepository;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) throws Exception {
        return employeeRepo.findById(id)
            .orElseThrow(() -> new Exception("Employee not found with id: " + id));
    }

    @Override
    public void createEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    @Override
    public void updateEmployeeById(Long id, Employee updatedEmployee) throws Exception {
        Employee employee = getEmployeeById(id);
        employee.setName(updatedEmployee.getName());
        employee.setPosition(updatedEmployee.getPosition());
        employee.setRole(updatedEmployee.getRole());
        employee.setDepartment(updatedEmployee.getDepartment());
        employee.setShift(updatedEmployee.getShift());
        employeeRepo.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepo.deleteById(id);
    }
}
