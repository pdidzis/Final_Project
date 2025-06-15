package lv.venta.service.impl;

import lv.venta.model.Department;
import lv.venta.repository.DepartmentRepository;
import lv.venta.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepo;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) throws Exception {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new Exception("Department not found with id: " + id));
    }

    @Override
    public void createDepartment(Department department) {
        departmentRepo.save(department);
    }

    @Override
    public void updateDepartmentById(Long id, Department updatedDepartment) throws Exception {
        Department department = getDepartmentById(id);
        department.setName(updatedDepartment.getName());
        department.setLocation(updatedDepartment.getLocation());
        departmentRepo.save(department);
    }

    @Override
    public void deleteDepartmentById(Long id) {
        departmentRepo.deleteById(id);
    }
}
