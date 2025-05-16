package api.endpoint.farmer_services.dto;

import api.endpoint.farmer_services.enums.DemandLevel;
import api.endpoint.farmer_services.model.CropType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketDataResponse {
    private Long id;
    private String cropName;
    private Double price;
    private DemandLevel demandLevel;
    private String currency;
    private LocalDateTime createdAt;
}
