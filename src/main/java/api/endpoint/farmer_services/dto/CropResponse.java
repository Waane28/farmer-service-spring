package api.endpoint.farmer_services.dto;

import api.endpoint.farmer_services.model.CropType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CropResponse {
    private Long id;
    private CropType cropType;
    private String description;
    private LocalDateTime plantingDate;
    private LocalDateTime haverstingDate;
}
