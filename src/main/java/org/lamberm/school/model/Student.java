package org.lamberm.school.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "PESEL", length = 11, unique = true, nullable = false)
    private String pesel;
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;
    @Column(name = "second_name", length = 20)
    private String secondName;
    @Column(name = "last_name", length = 100, nullable = false)
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
