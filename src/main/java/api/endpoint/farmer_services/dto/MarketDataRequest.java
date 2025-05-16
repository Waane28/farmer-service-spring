package api.endpoint.farmer_services.dto;

import api.endpoint.farmer_services.enums.DemandLevel;
import api.endpoint.farmer_services.model.CropType;
import lombok.Data;

@Data
public class MarketDataRequest {
    private String cropName;
    private Double price;
    private DemandLevel demandLevel;
    private String currency;
}
