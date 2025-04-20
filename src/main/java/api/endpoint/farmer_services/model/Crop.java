package api.endpoint.farmer_services.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "crop")
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn( name = "user_id", nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn( name = "crop_id", nullable = false)
    private CropType cropType;

    @Column( name = "planting_date")
    private LocalDateTime plantingDate = LocalDateTime.now();

    @Column( name = "havesting_date")
    private LocalDateTime haverstingDate = LocalDateTime.now();

    @Column( name = "description")
    private String description;

}
