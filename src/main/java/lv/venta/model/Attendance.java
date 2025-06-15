package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"aid"})
@Entity
@Table(name = "attendance_table")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aid")
    private Long aid;

    @ManyToOne
    @JoinColumn(name = "eid")
    @NotNull
    private Employee employee;

    @Column(name = "date")
    @NotNull
    private LocalDate date;

    public Attendance(Employee employee, LocalDate date) {
        setEmployee(employee);
        setDate(date);
    }
}
