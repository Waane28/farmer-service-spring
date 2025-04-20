package api.endpoint.farmer_services.controller;


import api.endpoint.farmer_services.dto.CropRequest;
import api.endpoint.farmer_services.dto.CropResponse;
import api.endpoint.farmer_services.dto.CropTypeResponse;
import api.endpoint.farmer_services.model.Crop;
import api.endpoint.farmer_services.repository.CropRepository;
import api.endpoint.farmer_services.services.CropService;
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
@RequestMapping("/api/v1.0/crops")
public class CropController {
    private final CropRepository cropRepository;
    private final CropService cropService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<CropResponse> createCrop(@Valid @RequestBody CropRequest cropRequest){
        Crop crop = modelMapper.map(cropRequest, Crop.class);
        Crop createCrop = cropService.createCrop(crop);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(crop, CropResponse.class));
    }

    @GetMapping
    public ResponseEntity<List<CropResponse>> getAllCrop() {
        List<CropResponse> crops = cropService.getAllCrop().stream()
                .map(crop -> modelMapper.map(crop, CropResponse.class))
                .toList();
        return ResponseEntity.ok(crops);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CropResponse> getCropById(@PathVariable Long id) {
        Crop crop = cropService.getCropById(id);
        return ResponseEntity.ok(modelMapper.map(crop, CropResponse.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CropResponse> updateCrop(@PathVariable Long id, @Valid @RequestBody CropRequest cropRequest) {
        Crop cropDetails = modelMapper.map(cropRequest, Crop.class);
        Crop updatedCrop = cropService.updateCrop(id, cropDetails);
        return ResponseEntity.ok(modelMapper.map(updatedCrop, CropResponse.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrop(@PathVariable Long id) {
        cropService.deleteCrop(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/totals")
    public Map<String, Long> getAllCrops() {
        return Map.of(
                "total_crops", cropRepository.count()
        );
    }

    @GetMapping("/all-crops-by-pagination")// Pagination
    public ResponseEntity<Map<String, Object>> getAllMarketDataPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Crop> pageCrops = cropService.getAllCropsByPaginated(page, size);

        List<CropTypeResponse> response = pageCrops.getContent()
                .stream()
                .map(cropType -> modelMapper.map(cropType, CropTypeResponse.class))
                .toList();

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", response);
        responseBody.put("total", pageCrops.getTotalElements());

        return ResponseEntity.ok(responseBody);
    }
}
