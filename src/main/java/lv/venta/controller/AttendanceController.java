package lv.venta.controller;

import lv.venta.model.Attendance;
import lv.venta.model.Employee;
import lv.venta.service.AttendanceService;
import lv.venta.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public String viewAllAttendance(Model model) {
        try {
        	model.addAttribute("box", attendanceService.getAllAttendances());

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
        }
        return "attendance-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("attendance", new Attendance());
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "attendance-form";
    }

    @PostMapping("/add")
    public String processAddForm(@Valid @ModelAttribute("attendance") Attendance attendance,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "attendance-form";
        }

        try {
            attendanceService.createAttendance(attendance);
            return "redirect:/attendance";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "attendance-form";
        }
    }

    @GetMapping("/remove/{id}")
    public String removeAttendance(@PathVariable("id") long id, Model model) {
        try {
            attendanceService.deleteAttendanceById(id);
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
        }
        return "redirect:/attendance";
    }


}
