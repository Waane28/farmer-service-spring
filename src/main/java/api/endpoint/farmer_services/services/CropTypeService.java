package api.endpoint.farmer_services.services;

import api.endpoint.farmer_services.model.CropType;
import api.endpoint.farmer_services.repository.CropTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CropTypeService {
    private final CropTypeRepository cropTypeRepository;

    public CropType createCropType(CropType cropType) {
        return cropTypeRepository.save(cropType);
    }

    public List<CropType> getAllCropTypes() {
        return cropTypeRepository.findAll();
    }


    public CropType getCropTypeById(Long id) {
        return cropTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop Type not found"));
    }

    public CropType updateCropType(Long id, CropType cropTypeDetails) {
        return cropTypeRepository.findById(id)
                .map(cropType -> {
                    cropType.setName(cropTypeDetails.getName());
                    cropType.setDescriptions(cropTypeDetails.getDescriptions());
                    return cropTypeRepository.save(cropType);
                })
                .orElseThrow(() -> new RuntimeException("Crop Type not found"));
    }

    public void deleteCropType(Long id) {
        cropTypeRepository.deleteById(id);
    }

    public Page<CropType> getAllCropTypePaginated(int page, int size) {
        return cropTypeRepository.findAll(PageRequest.of(page, size));
    }
}
