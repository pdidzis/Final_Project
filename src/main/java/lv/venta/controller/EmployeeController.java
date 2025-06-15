package lv.venta.controller;

import lv.venta.model.Employee;
import lv.venta.model.Department;
import lv.venta.model.Shift;
import lv.venta.model.enums.Role;
import lv.venta.repository.EmployeeRepository;
import lv.venta.repository.DepartmentRepository;
import lv.venta.repository.ShiftRepository;
import lv.venta.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired private EmployeeRepository employeeRepo;
    @Autowired private DepartmentRepository departmentRepo;
    @Autowired private ShiftRepository shiftRepo;
    @Autowired private AttendanceRepository attendanceRepo;

    @GetMapping("")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeRepo.findAll());
        return "employee-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("roles", Role.values());
        model.addAttribute("departments", departmentRepo.findAll());
        model.addAttribute("shifts", shiftRepo.findAll());
        return "employee-form";
    }

    @PostMapping({"/add","/edit/{eid}"})
    public String saveEmployee(@PathVariable(required=false) Long eid,
                               @ModelAttribute Employee employee) {
        if (eid != null) employee.setEid(eid);
        Department dept = departmentRepo
            .findById(employee.getDepartment().getDid())
            .orElseThrow();
        employee.setDepartment(dept);

        if (employee.getShift() != null && employee.getShift().getSid() != null) {
            Shift sh = shiftRepo.findById(employee.getShift().getSid()).orElse(null);
            employee.setShift(sh);
        } else {
            employee.setShift(null);
        }

        employeeRepo.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/edit/{eid}")
    public String showEditForm(@PathVariable Long eid, Model model) {
        Employee e = employeeRepo.findById(eid).orElseThrow();
        model.addAttribute("employee", e);
        model.addAttribute("roles", Role.values());
        model.addAttribute("departments", departmentRepo.findAll());
        model.addAttribute("shifts", shiftRepo.findAll());
        return "employee-form";
    }

    @GetMapping("/delete/{eid}")
    public String deleteEmployee(@PathVariable Long eid) {
        attendanceRepo.deleteByEmployeeEid(eid);
        employeeRepo.deleteById(eid);
        return "redirect:/employee";
    }
}
