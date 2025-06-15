package lv.venta.controller;

import lv.venta.model.Shift;
import lv.venta.model.Employee;
import lv.venta.repository.ShiftRepository;
import lv.venta.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shift")
public class ShiftController {

    @Autowired
    private ShiftRepository shiftRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @GetMapping("")
    public String showAllShifts(Model model) {
        try {
            model.addAttribute("shifts", shiftRepo.findAll());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "shift-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("shift", new Shift());
        return "shift-form";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee, @RequestParam Long shiftId) {
        Shift shift = shiftRepo.findById(shiftId).orElse(null);
        employee.setShift(shift);
        employeeRepo.save(employee);
        return "redirect:/employee";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Shift shift = shiftRepo.findById(id).orElse(null);
            if (shift == null) {
                model.addAttribute("error", "Shift not found");
                return "error-page";
            }
            model.addAttribute("shift", shift);
            return "shift-form";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }

    @PostMapping("/edit/{id}")
    public String editShift(@PathVariable Long id, @ModelAttribute Shift shift, Model model) {
        try {
            shift.setSid(id);
            shiftRepo.save(shift);
            return "redirect:/shift";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "shift-form";
        }
    }


    @GetMapping("/employees")
    public String getEmployeesByShiftId(@RequestParam("id") Long id, Model model) {
        try {
            model.addAttribute("shifts", shiftRepo.findAll());

            Shift shift = shiftRepo.findById(id).orElse(null);
            if (shift != null) {
                List<Employee> assigned = employeeRepo.findAll().stream()
                        .filter(e -> e.getShift() != null && e.getShift().getSid() == id)
                        .toList();
                model.addAttribute("employees", assigned);
            } else {
                model.addAttribute("error", "Shift not found");
                model.addAttribute("employees", null);
            }

            return "shift-list";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error-page";
        }
    }
}
