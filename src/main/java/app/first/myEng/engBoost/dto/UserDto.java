package app.first.myEng.engBoost.dto;

import app.first.myEng.engBoost.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "Id can't be null", groups = OnUpdate.class)
    private Integer id;

    @NotNull(message = "First name must be not null")
    @Length(min = 2, max = 100, message = "First name should be in range between 2 and 100 chars")
    private String firstName;
    @NotNull(message = "Last name must be not null")
    @Length(min = 2, max = 100, message = "Second name should be in range between 2 and 100 chars")
    private String lastName;
    @NotNull(message = "Username must be not null")
    @Length(min = 10, max = 150, message = "Username must be in range between 10 and 150 chars")
    private String username;
    @NotNull(message = "Age must be not null")
    @Min(value = 6, message = "Age should be greater than 5")
    private int age;
    @NotNull(message = "Email must be not null")
    @Length(max = 150, message = "Max possible length is 150 chars")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Password can't be null")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotNull(message = "Password confirmation can't be null")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordConfirmation;
}
