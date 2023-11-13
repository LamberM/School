package org.lamberm.school.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
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
    @Transient
    private String fullName;

    public String getFullName() {
        if (secondName == null) {
            return firstName + " " + lastName;
        } else {
            return firstName + " " + secondName + " " + lastName;
        }
    }
}
