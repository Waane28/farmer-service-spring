package api.endpoint.farmer_services.dto;

import api.endpoint.farmer_services.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private UserRole userRole;
    private String village;
}
