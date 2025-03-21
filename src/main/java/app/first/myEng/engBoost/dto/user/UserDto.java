package app.first.myEng.engBoost.dto.user;

import app.first.myEng.engBoost.validation.OnCreate;
import app.first.myEng.engBoost.validation.OnUpdate;
import app.first.myEng.engBoost.validation.UniqueUserField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({
        "id", "username", "firstName",
        "lastName", "age", "email"
})
public class UserDto {
    @NotNull(message = "Id can't be null", groups = OnUpdate.class)
    private Integer id;

    @NotNull(message = "First name must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 2, max = 100, message = "First name should be in range between 2 and 100 chars", groups = {OnCreate.class, OnUpdate.class})
    private String firstName;
    @NotNull(message = "Last name must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 2, max = 100, message = "Second name should be in range between 2 and 100 chars", groups = {OnCreate.class, OnUpdate.class})
    private String lastName;
    @NotNull(message = "Username must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 10, max = 150, message = "Username must be in range between 10 and 150 chars", groups = {OnCreate.class, OnUpdate.class})
    @UniqueUserField(fieldName = "username", message = "User with such username already exists", groups = {OnCreate.class, OnUpdate.class})
    private String username;
    @NotNull(message = "Age must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Min(value = 6, message = "Age should be greater than 5")
    private int age;
    @NotNull(message = "Email must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 150, message = "Max possible length is 150 chars", groups = {OnCreate.class, OnUpdate.class})
    @Email(message = "Invalid email format", groups = {OnCreate.class, OnUpdate.class})
    @UniqueUserField(fieldName = "email", message = "User with such email already exists", groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @NotNull(message = "Password can't be null", groups = {OnCreate.class, OnUpdate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotNull(message = "Password confirmation can't be null", groups = OnCreate.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordConfirmation;
}
