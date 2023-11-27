package org.lamberm.school.teacher;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.lamberm.school.util.SchoolSubject;

@Entity
@Data
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PESEL", length = 11, unique = true, nullable = false)
    private String pesel;

    @Column(length = 20, nullable = false)
    private String firstName;

    @Column(length = 20)
    private String secondName;

    @Column(length = 100, nullable = false)
    private String lastName;

    private SchoolSubject schoolSubject;
}
