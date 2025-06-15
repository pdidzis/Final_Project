package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"did"})
@Table(name = "department_table")
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long did; // NOW allowed to set in controller

    @Column(name = "name")
    @NotNull
    @Pattern(regexp = "[A-Z][a-zA-Z]*", message = "must start with an uppercase letter followed by letters")
    @Size(min = 1, max = 100)
    private String name;

    @Column(name = "location")
    @NotNull
    private String location;

    public Department(String name, String location) {
        setName(name);
        setLocation(location);
    }
}
