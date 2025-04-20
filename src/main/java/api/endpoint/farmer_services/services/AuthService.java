package api.endpoint.farmer_services.services;

import api.endpoint.farmer_services.dto.SignupRequest;
import api.endpoint.farmer_services.dto.UserDto;
import api.endpoint.farmer_services.enums.UserRole;
import api.endpoint.farmer_services.model.Users;
import api.endpoint.farmer_services.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    public UserDto createUser(SignupRequest signupRequest) {
        Users user = new Users();
        // Map all fields from SignupRequest to User entity
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setFullName(signupRequest.getFullName());
        user.setVillage(signupRequest.getVillage());
        user.setUserRole(UserRole.FARMER); // Default role for new users

        Users savedUser = userRepository.save(user);
        return convertToUserDto(savedUser);
    }

    private UserDto convertToUserDto(Users user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setFullName(user.getFullName());
        userDto.setVillage(user.getVillage());
        userDto.setUserRole(user.getUserRole());
        // Do NOT include password in the DTO
        return userDto;
    }
}
