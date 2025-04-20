package api.endpoint.farmer_services.controller;


import api.endpoint.farmer_services.dto.CropTypeRequest;
import api.endpoint.farmer_services.dto.CropTypeResponse;
import api.endpoint.farmer_services.model.CropType;
import api.endpoint.farmer_services.repository.CropTypeRepository;
import api.endpoint.farmer_services.services.CropTypeService;
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
@RequestMapping("/api/v1.0/crop-types")
public class CropTypeContoller {
    private final CropTypeService cropTypeService;
    private final CropTypeRepository cropTypeRepository;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<CropTypeResponse> createCropType(@Valid @RequestBody CropTypeRequest cropTypeRequest) {
        CropType cropType = modelMapper.map(cropTypeRequest, CropType.class);
        CropType createCropType = cropTypeService.createCropType(cropType);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(cropType, CropTypeResponse.class));
    }

    @GetMapping
    public ResponseEntity<List<CropTypeResponse>> getAllCropTypes() {
        List<CropTypeResponse> cropTypes = cropTypeService.getAllCropTypes().stream()
                .map(cropType -> modelMapper.map(cropType, CropTypeResponse.class))
                .toList();
        return ResponseEntity.ok(cropTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropTypeResponse> getCropTypeById(@PathVariable Long id) {
        CropType cropType = cropTypeService.getCropTypeById(id);
        return ResponseEntity.ok(modelMapper.map(cropType, CropTypeResponse.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CropTypeResponse> updateCropType(@PathVariable Long id, @Valid @RequestBody CropTypeRequest cropTypeRequest) {
        CropType cropTypeDetails = modelMapper.map(cropTypeRequest, CropType.class);
        CropType updatedCropType = cropTypeService.updateCropType(id, cropTypeDetails);
        return ResponseEntity.ok(modelMapper.map(updatedCropType, CropTypeResponse.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCropType(@PathVariable Long id) {
        cropTypeService.deleteCropType(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/totals")
    public Map<String, Long> getAllTypes() {
        return Map.of(
                "total_crop_types", cropTypeRepository.count()
        );
    }

    @GetMapping("/all-types-by-pagination")// Pagination
    public ResponseEntity<Map<String, Object>> getAllCropTypePagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<CropType> pageCropTypes = cropTypeService.getAllCropTypePaginated(page, size);

        List<CropTypeResponse> response = pageCropTypes.getContent()
                .stream()
                .map(cropType -> modelMapper.map(cropType, CropTypeResponse.class))
                .toList();

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", response);
        responseBody.put("total", pageCropTypes.getTotalElements());

        return ResponseEntity.ok(responseBody);
    }
}
