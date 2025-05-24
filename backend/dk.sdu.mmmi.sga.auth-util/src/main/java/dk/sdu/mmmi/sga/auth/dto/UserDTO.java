package dk.sdu.mmmi.sga.auth.dto;

import dk.sdu.mmmi.sga.auth.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String password;
    private Roles role;
}
