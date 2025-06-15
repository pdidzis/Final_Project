package lv.venta;

import lv.venta.model.Attendance;
import lv.venta.model.Department;
import lv.venta.model.Employee;
import lv.venta.model.Leave;
import lv.venta.model.Shift;
import lv.venta.model.enums.Role;
import lv.venta.repository.AttendanceRepository;
import lv.venta.repository.DepartmentRepository;
import lv.venta.repository.EmployeeRepository;
import lv.venta.repository.LeaveRepository;
import lv.venta.repository.ShiftRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

@SpringBootApplication
public class FinalProjectApplication {

	public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner testModel(DepartmentRepository departmentRepo, EmployeeRepository employeeRepo,
                                       ShiftRepository shiftRepo, AttendanceRepository attendanceRepo,
                                       LeaveRepository leaveRepo) {
        return args -> {

            // === Departments ===
            Department d1 = new Department("IT", "2nd Floor");
            Department d2 = new Department("HR", "1st Floor");
            Department d3 = new Department("Logistics", "3rd Floor");
            departmentRepo.saveAll(Arrays.asList(d1, d2, d3));

            // === Shifts ===
            Shift s1 = new Shift("Morning", LocalTime.of(8, 0), LocalTime.of(16, 0));
            Shift s2 = new Shift("Evening", LocalTime.of(16, 0), LocalTime.of(0, 0));
            Shift s3 = new Shift("Night", LocalTime.of(0, 0), LocalTime.of(8, 0));
            Shift s4 = new Shift("Not assigned", LocalTime.of(0, 0), LocalTime.of(0, 1));
            shiftRepo.saveAll(Arrays.asList(s1, s2, s3, s4));

            // === Employees ===
            Employee e1 = new Employee("John", "Doe", "Developer", Role.ADMIN, d1, s1);
            Employee e2 = new Employee("Jane", "Smith", "Floor Manager", Role.MANAGER, d2, s2);
            Employee e3 = new Employee("Mike", "Brown", "Intern", Role.STAFF, d3, s3);
            Employee e4 = new Employee("Sara", "White", "Security", Role.SECURITY, d1, s1);
            Employee e5 = new Employee("Emma", "Green", "Security Supervisor", Role.SUPERVISOR, d2, s2);
            employeeRepo.saveAll(Arrays.asList(e1, e2, e3, e4, e5));

            // === Attendance ===
            Attendance a1 = new Attendance(e1, LocalDate.of(2025, 6, 1));
            Attendance a2 = new Attendance(e1, LocalDate.of(2025, 6, 2));
            Attendance a3 = new Attendance(e2, LocalDate.of(2025, 6, 1));
            Attendance a4 = new Attendance(e3, LocalDate.of(2025, 6, 10));
            Attendance a5 = new Attendance(e2, LocalDate.of(2025, 6, 8));
            attendanceRepo.saveAll(Arrays.asList(a1, a2, a3, a4, a5));

            // === Leave ===
            Leave l1 = new Leave(e1, LocalDate.of(2025, 6, 5), LocalDate.of(2025, 6, 10));
            Leave l2 = new Leave(e2, LocalDate.of(2025, 6, 15), LocalDate.of(2025, 6, 20));
            leaveRepo.saveAll(Arrays.asList(l1, l2));

            System.out.println("=== Departments, Employees, Attendance, and Leave Initialized ===");
        };
    }
}
