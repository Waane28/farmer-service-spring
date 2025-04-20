package api.endpoint.farmer_services.repository;

import api.endpoint.farmer_services.model.MarketData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MarketDataRepository extends BaseRepository<MarketData, Long> {
    Page<MarketData> findAll(Pageable pageable);
    long count();
}
