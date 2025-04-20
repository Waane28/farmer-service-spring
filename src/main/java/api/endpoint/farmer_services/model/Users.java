package api.endpoint.farmer_services.model;

import api.endpoint.farmer_services.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usernames")
    private String username;

    @Column(unique = true, name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_roles")
    private UserRole userRole;// FARMER or ADMIN

    @Column(name = "village")
    private String village;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Relationships
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Crop> crop;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Notifications> notifications;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    // Make sure other UserDetails methods (isEnabled, etc.) return true
    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    @Override
    public String getUsername() {
        return this.email; // Use email as the username for Spring Security
    }

    // Keep existing getters for the 'username' field if needed elsewhere
    public String getDisplayUsername() {
        return this.username;
    }

}

