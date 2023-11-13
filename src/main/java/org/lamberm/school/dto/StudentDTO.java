package org.lamberm.school.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.pl.PESEL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    @PESEL
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
