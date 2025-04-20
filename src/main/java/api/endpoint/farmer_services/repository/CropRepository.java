package api.endpoint.farmer_services.repository;

import api.endpoint.farmer_services.model.Crop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CropRepository extends BaseRepository<Crop, Long> {
    Page<Crop> findAll(Pageable pageable);
    long count();
}
