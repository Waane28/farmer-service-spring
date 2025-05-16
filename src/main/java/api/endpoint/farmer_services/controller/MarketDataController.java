package api.endpoint.farmer_services.controller;

import api.endpoint.farmer_services.dto.MarketDataRequest;
import api.endpoint.farmer_services.dto.MarketDataResponse;
import api.endpoint.farmer_services.model.MarketData;
import api.endpoint.farmer_services.repository.MarketDataRepository;
import api.endpoint.farmer_services.services.MarketDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/market-data")
public class MarketDataController {
    private final MarketDataService marketDataService;
    private final ModelMapper modelMapper;
    private final MarketDataRepository marketDataRepository;

    @PostMapping
    public ResponseEntity<MarketDataResponse> createMarketData(@Valid @RequestBody MarketDataRequest marketDataRequest) {
        MarketData createdMarketData = marketDataService.createMarketData(marketDataRequest);
        MarketDataResponse response = modelMapper.map(createdMarketData, MarketDataResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MarketDataResponse>> getAllMarketData() {
        List<MarketDataResponse> marketDatas= marketDataService.getAllMarketData().stream()
                .map(marketData -> modelMapper.map(marketData, MarketDataResponse.class))
                .toList();
        return ResponseEntity.ok(marketDatas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarketDataResponse> getMarketDataById(@PathVariable Long id) {
        MarketData marketData = marketDataService.getMarketDataById(id);
        return ResponseEntity.ok(modelMapper.map(marketData, MarketDataResponse.class));
    }

    @GetMapping("/summary") // Updated path
    public Map<String, Long> getMarketDataSummary() {
        long totalPriceIncreased = marketDataRepository.countByPriceGreaterThan(50.0);
        long totalPriceDecreased = marketDataRepository.countByPriceLessThan(-50.0);

        return Map.of(
                "total_price_increased", totalPriceIncreased,
                "total_price_decreased", totalPriceDecreased
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarketDataResponse> updateMarketData(@PathVariable Long id, @Valid @RequestBody MarketDataRequest marketDataRequest) {
        MarketData marketDataDetails = modelMapper.map(marketDataRequest, MarketData.class);
        MarketData updateMarketData = marketDataService.updateMarketData(id, marketDataDetails);
        return ResponseEntity.ok(modelMapper.map(updateMarketData, MarketDataResponse.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarketData(@PathVariable Long id) {
        marketDataService.deleteMarketData(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/pagination")
    public ResponseEntity<Map<String, Object>> getAllMarketDataPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<MarketData> pageMarketDatas = marketDataService.getAllMarketDataPaginated(page, size);

        List<MarketDataResponse> response = pageMarketDatas.getContent()
                .stream()
                .map(marketData -> modelMapper.map(marketData, MarketDataResponse.class))
                .toList();

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", response);
        responseBody.put("total", pageMarketDatas.getTotalElements());

        return ResponseEntity.ok(responseBody);
    }

}
