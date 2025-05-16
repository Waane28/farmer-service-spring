package api.endpoint.farmer_services.repository;

import api.endpoint.farmer_services.model.MarketData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MarketDataRepository extends BaseRepository<MarketData, Long> {
    Page<MarketData> findAll(Pageable pageable);
    long count();
    // Count entries where price is greater than 50.0 (increase)
    Long countByPriceGreaterThan(Double price);

    // Count entries where price is less than -50.0 (decrease)
    Long countByPriceLessThan(Double price);
}
