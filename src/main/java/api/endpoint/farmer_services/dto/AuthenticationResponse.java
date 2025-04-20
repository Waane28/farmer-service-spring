package api.endpoint.farmer_services.dto;


import api.endpoint.farmer_services.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private Long id;
    private String jwt;
    private String fullName;
    private String email;
    private UserRole userRole; // "FARMER" or "ADMIN"
    private String village;
}
