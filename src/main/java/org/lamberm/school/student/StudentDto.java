package org.lamberm.school.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    @Pattern(regexp = "^[0-9]+$", message = "PESEL must be digits")
    @NotBlank(message = "PESEL must not be blank")
    @Size(min = 11, max = 11, message = "PESEL length requires 11 characters")
    private String pesel;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
    @NotBlank(message = "First name must not be blank")
    @Size(min = 2, max = 20, message = "First name length must not be less than 2 and more than 20")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Second name must contain only letters")
    @Size(min = 2, max = 20, message = "Second name length must not be less than 2 and more than 20")
    private String secondName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
    @NotBlank(message = "Last name must not be blank")
    @Size(min = 3, max = 100, message = "Last name length must not be less than 3 and more than 100")
    private String lastName;
}
