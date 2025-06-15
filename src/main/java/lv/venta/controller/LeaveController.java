package lv.venta.controller;

import lv.venta.model.Leave;
import lv.venta.repository.EmployeeRepository;
import lv.venta.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveRepository leaveRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    // List all leaves
    @GetMapping
    public String getAllLeaves(Model model) {
        model.addAttribute("leaves", leaveRepo.findAll());
        return "leave-list";
    }

    // Show form to add a leave
    @GetMapping("/add")
    public String addLeaveForm(Model model) {
        model.addAttribute("leave", new Leave());
        model.addAttribute("employees", employeeRepo.findAll());
        return "leave-form";
    }

    // Handle add form submission
    @PostMapping("/add")
    public String addLeave(@ModelAttribute Leave leave, Model model) {
        if (leave.getEndDate().isBefore(leave.getStartDate())) {
            model.addAttribute("error", "End date cannot be before start date.");
            model.addAttribute("employees", employeeRepo.findAll());
            return "leave-form";
        }
        leaveRepo.save(leave);
        return "redirect:/leave";
    }

    // Delete leave by ID
    @GetMapping("/delete/{id}")
    public String deleteLeave(@PathVariable Long id) {
        if (leaveRepo.existsById(id)) {
            leaveRepo.deleteById(id);
        }
        return "redirect:/leave";
    }

    // Show form to edit a leave
    @GetMapping("/edit/{id}")
    public String editLeaveForm(@PathVariable Long id, Model model) {
        Optional<Leave> optionalLeave = leaveRepo.findById(id);
        if (optionalLeave.isEmpty()) {
            return "redirect:/leave";
        }
        model.addAttribute("leave", optionalLeave.get());
        model.addAttribute("employees", employeeRepo.findAll());
        return "leave-form";
    }

    // Handle update submission
    @PostMapping("/edit/{id}")
    public String updateLeave(@PathVariable Long id, @ModelAttribute Leave leave, Model model) {
        if (leave.getEndDate().isBefore(leave.getStartDate())) {
            model.addAttribute("error", "End date cannot be before start date.");
            model.addAttribute("employees", employeeRepo.findAll());
            model.addAttribute("leave", leave);
            return "leave-form";
        }
        leave.setLid(id);
        leaveRepo.save(leave);
        return "redirect:/leave";
    }
}
