package lv.venta.model;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lv.venta.model.enums.Role;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"eid"})
@Entity
@Table(name = "employee_table")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eid")
    private Long eid;

    @Column(name = "name")
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+", message = "Name must start with a capital letter and contain only letters")
    @Size(min = 1, max = 50)
    private String name;

    @Column(name = "surname")
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+", message = "Surname must start with a capital letter and contain only letters")
    @Size(min = 1, max = 50)
    private String surname;

    @Column(name = "position")
    @NotNull
    private String position;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @NotNull
    private Role role;

    @ManyToOne
    @JoinColumn(name = "did")
    @NotNull
    private Department department;

    @ManyToOne
    @JoinColumn(name = "sid")
    @NotNull
    private Shift shift;
    
    @OneToMany(mappedBy="employee", cascade=CascadeType.REMOVE, orphanRemoval=true)  //so i wasnt able to delete remove employee because
                                                  //it was parent of attendance, after researching it i learned i need to use this
    														
    private List<Attendance> attendances;


    public Employee(String name, String surname, String position, Role role, Department department, Shift shift) {
        setName(name);
        setSurname(surname);
        setPosition(position);
        setRole(role);
        setDepartment(department);
        setShift(shift);
    }
}
