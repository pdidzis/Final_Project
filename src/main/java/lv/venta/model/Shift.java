package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"sid"})
@Table(name = "shift_table")
@Entity
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    private Long sid;

    @Column(name = "name")
    @NotNull
    private String name; // 1.shift or morning shift ect.

    @Column(name = "start_time")
    @NotNull
    private LocalTime startTime;

    @Column(name = "end_time")
    @NotNull
    private LocalTime endTime;

    public Shift(String name, LocalTime startTime, LocalTime endTime) {
        setName(name);
        setStartTime(startTime);
        setEndTime(endTime);
    }
}
