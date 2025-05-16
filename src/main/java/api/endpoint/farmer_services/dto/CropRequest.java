package api.endpoint.farmer_services.dto;

import api.endpoint.farmer_services.model.CropType;
import lombok.Data;

@Data
public class CropRequest {
    private String cropName;
    private String description;
}
