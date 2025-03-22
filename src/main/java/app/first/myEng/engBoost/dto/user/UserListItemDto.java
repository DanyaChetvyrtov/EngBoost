package app.first.myEng.engBoost.dto.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "id", "username", "email"
})
public class UserListItemDto {
    private Integer id;
    private String username;
    private String email;
}
