package org.lamberm.school.student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
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
}
