package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "leave_table")
@Entity
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lid")
    private Long lid;

    @ManyToOne
    @JoinColumn(name = "eid")
    @NotNull
    private Employee employee;

    @Column(name = "start_date")
    @NotNull
    private LocalDate startDate;

    @Column(name = "end_date")
    @NotNull
    private LocalDate endDate;



    public Leave(Employee employee, LocalDate startDate, LocalDate endDate) {
        setEmployee(employee);
        setStartDate(startDate);
        setEndDate(endDate);

    }
}
