package api.endpoint.farmer_services.repository;

import api.endpoint.farmer_services.model.CropType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CropTypeRepository extends BaseRepository<CropType, Long> {
    Page<CropType> findAll(Pageable pageable);
    Optional<CropType> findByName(String name);
    //    Counts By Query
    long count();
//    Long countByType( type);
}
