package api.endpoint.farmer_services.services;

import api.endpoint.farmer_services.model.Crop;
import api.endpoint.farmer_services.repository.CropRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CropService {
    private final CropRepository cropRepository;

    public Crop createCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    public List<Crop> getAllCrop() {
        return cropRepository.findAll();
    }

    public Crop getCropById(Long id) {
        return cropRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop not found"));
    }

    public Crop updateCrop(Long id, Crop cropDetails) {
        return cropRepository.findById(id)
                .map(crop -> {
                    crop.setDescription(cropDetails.getDescription());
                    return cropRepository.save(crop);
                })
                .orElseThrow(() -> new RuntimeException("crop not found"));
    }

    public void deleteCrop(Long id) {
        cropRepository.deleteById(id);
    }

    public Page<Crop> getAllCropsByPaginated(int page, int size) {
        return cropRepository.findAll(PageRequest.of(page, size));
    }

}
