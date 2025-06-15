package lv.venta.controller;

import lv.venta.model.Department;
import lv.venta.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Main page
    @GetMapping("") 
    public String getAllDepartmentsPage(Model model) {
        try {
            model.addAttribute("box", departmentService.getAllDepartments());
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
        }
        return "departments";
    }

    // Adding http://localhost:8080/department
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department());
        return "department-form";
    }

    @PostMapping("/add")
    public String submitNewDepartment(@Valid @ModelAttribute Department department,
                                      BindingResult result,
                                      Model model) {
        if (result.hasErrors()) {
            model.addAttribute("department", department);
            return "department-form";
        }

        try {
            departmentService.createDepartment(department);
            return "redirect:/department";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "department-form";
        }
    }

    // ================== UPDATE FORM ==================
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        try {
            Department dept = departmentService.getDepartmentById(id);
            model.addAttribute("department", dept);
            return "department-form";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "departments";
        }
    }

    @PostMapping("/update/{id}")
    public String submitUpdate(@PathVariable("id") long id,
                               @Valid @ModelAttribute Department department,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("department", department);
            return "department-form";
        }

        try {
            department.setDid(id); // Set ID before update
            departmentService.updateDepartmentById(id, department);
            return "redirect:/department";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "department-form";
        }
    }

    // ================== DELETE ==================
    @GetMapping("/remove/{id}")
    public String deleteDepartment(@PathVariable("id") long id, Model model) {
        try {
            departmentService.deleteDepartmentById(id);
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
        }
        return "redirect:/department";
    }
}
