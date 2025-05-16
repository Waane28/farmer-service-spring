package api.endpoint.farmer_services.services;


import api.endpoint.farmer_services.dto.MarketDataRequest;
import api.endpoint.farmer_services.model.Crop;
import api.endpoint.farmer_services.model.CropType;
import api.endpoint.farmer_services.model.MarketData;
import api.endpoint.farmer_services.repository.CropTypeRepository;
import api.endpoint.farmer_services.repository.MarketDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketDataService {
    private final MarketDataRepository marketDataRepository;
    private final CropTypeRepository cropTypeRepository;

    public MarketData createMarketData(MarketDataRequest request) {
        CropType cropType = cropTypeRepository.findByName(request.getCropName())
                .orElseThrow(() -> new RuntimeException("CropType not found: " + request.getCropName()));
        MarketData marketData = new MarketData();
        marketData.setCropType(cropType);
        marketData.setPrice(request.getPrice());
        marketData.setDemandLevel(request.getDemandLevel());
        marketData.setCurrency(request.getCurrency());
        return marketDataRepository.save(marketData);
    }

    public List<MarketData> getAllMarketData() {
        return marketDataRepository.findAll();
    }

    public MarketData getMarketDataById(Long id) {
        return marketDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Market Data not found"));
    }

    public MarketData updateMarketData(Long id, MarketData marketDataDetails) {
        return marketDataRepository.findById(id)
                .map(marketData -> {
                    marketData.setPrice(marketDataDetails.getPrice());
                    marketData.setDemandLevel(marketDataDetails.getDemandLevel());
                    marketData.setCurrency(marketDataDetails.getCurrency());
                    return marketDataRepository.save(marketData);
                })
                .orElseThrow(() -> new RuntimeException("Market Data not found"));
    }

    public void deleteMarketData(Long id) {
        marketDataRepository.deleteById(id);
    }

    public Page<MarketData> getAllMarketDataPaginated(int page, int size) {
        return marketDataRepository.findAll(PageRequest.of(page, size));
    }
}
