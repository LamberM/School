package LamberM.School.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
//    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer number;
    private String firstName;
    private String secondName;
    private String lastName;
    @Transient
    private String fullName;

        public String getFullName() {
            if (secondName == null) {
                return firstName + " " + lastName;
            }
            else {
                return firstName + " " + secondName + " " + lastName;
            }
        }
}
