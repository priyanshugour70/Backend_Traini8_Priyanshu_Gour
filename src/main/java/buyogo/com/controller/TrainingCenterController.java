package buyogo.com.controller;

import buyogo.com.dto.TrainingCenterCreateDTO;
import buyogo.com.dto.TrainingCenterDTO;
import buyogo.com.dto.TrainingCenterUpdateDTO;
import buyogo.com.dto.request.TrainingCenterFilterRequest;
import buyogo.com.dto.response.ApiResponse;
import buyogo.com.service.TrainingCenterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/training-centers")
@Slf4j
@RequiredArgsConstructor
public class TrainingCenterController {

    private final TrainingCenterService trainingCenterService;

    @PostMapping
    public ResponseEntity<ApiResponse<TrainingCenterDTO>> createTrainingCenter(
            @Valid @RequestBody TrainingCenterCreateDTO dto) {
        log.info("Received request to create training center: {}", dto.getCenterName());

        try {
            TrainingCenterDTO createdCenter = trainingCenterService.createTrainingCenter(dto);
            ApiResponse<TrainingCenterDTO> response = ApiResponse.created(
                    "Training center created successfully",
                    createdCenter
            );

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error in createTrainingCenter: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<TrainingCenterDTO>>> getAllTrainingCenters(
            @PageableDefault(size = 10, sort = "centerName") Pageable pageable) {
        log.info("Received request to get all training centers");

        try {
            Page<TrainingCenterDTO> centers = trainingCenterService.getAllTrainingCenters(pageable);
            ApiResponse<Page<TrainingCenterDTO>> response = ApiResponse.success(
                    "Training centers retrieved successfully",
                    centers
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in getAllTrainingCenters: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TrainingCenterDTO>> getTrainingCenterById(@PathVariable Long id) {
        log.info("Received request to get training center with ID: {}", id);

        try {
            TrainingCenterDTO center = trainingCenterService.getTrainingCenterById(id);
            ApiResponse<TrainingCenterDTO> response = ApiResponse.success(
                    "Training center retrieved successfully",
                    center
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in getTrainingCenterById: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/code/{centerCode}")
    public ResponseEntity<ApiResponse<TrainingCenterDTO>> getTrainingCenterByCode(@PathVariable String centerCode) {
        log.info("Received request to get training center with code: {}", centerCode);

        try {
            TrainingCenterDTO center = trainingCenterService.getTrainingCenterByCode(centerCode);
            ApiResponse<TrainingCenterDTO> response = ApiResponse.success(
                    "Training center retrieved successfully",
                    center
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in getTrainingCenterByCode: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<ApiResponse<Page<TrainingCenterDTO>>> getTrainingCentersByCity(
            @PathVariable String city,
            @PageableDefault(size = 10) Pageable pageable) {
        log.info("Received request to get training centers in city: {}", city);

        try {
            Page<TrainingCenterDTO> centers = trainingCenterService.getTrainingCentersByCity(city, pageable);
            ApiResponse<Page<TrainingCenterDTO>> response = ApiResponse.success(
                    "Training centers retrieved for city: " + city,
                    centers
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in getTrainingCentersByCity: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<TrainingCenterDTO>>> searchTrainingCenters(
            @RequestParam String keyword,
            @PageableDefault(size = 10) Pageable pageable) {
        log.info("Received request to search training centers with keyword: {}", keyword);

        try {
            Page<TrainingCenterDTO> centers = trainingCenterService.searchTrainingCenters(keyword, pageable);
            ApiResponse<Page<TrainingCenterDTO>> response = ApiResponse.success(
                    "Search results for: " + keyword,
                    centers
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in searchTrainingCenters: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/course/{course}")
    public ResponseEntity<ApiResponse<Page<TrainingCenterDTO>>> getTrainingCentersByCourse(
            @PathVariable String course,
            @PageableDefault(size = 10) Pageable pageable) {
        log.info("Received request to get training centers offering course: {}", course);

        try {
            Page<TrainingCenterDTO> centers = trainingCenterService.getTrainingCentersByCourse(course, pageable);
            ApiResponse<Page<TrainingCenterDTO>> response = ApiResponse.success(
                    "Training centers offering: " + course,
                    centers
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in getTrainingCentersByCourse: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<Page<TrainingCenterDTO>>> filterTrainingCenters(
            @ModelAttribute TrainingCenterFilterRequest filterRequest,
            @PageableDefault(size = 10) Pageable pageable) {
        log.info("Received request to filter training centers: {}", filterRequest);

        try {
            Page<TrainingCenterDTO> centers = trainingCenterService.filterTrainingCenters(filterRequest, pageable);
            ApiResponse<Page<TrainingCenterDTO>> response = ApiResponse.success(
                    "Training centers filtered successfully",
                    centers
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in filterTrainingCenters: {}", e.getMessage());
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TrainingCenterDTO>> updateTrainingCenter(
            @PathVariable Long id,
            @Valid @RequestBody TrainingCenterUpdateDTO dto) {
        log.info("Received request to update training center with ID: {}", id);

        try {
            TrainingCenterDTO updatedCenter = trainingCenterService.updateTrainingCenter(id, dto);
            ApiResponse<TrainingCenterDTO> response = ApiResponse.success(
                    "Training center updated successfully",
                    updatedCenter
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in updateTrainingCenter: {}", e.getMessage());
            throw e;
        }
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<ApiResponse<TrainingCenterDTO>> activateTrainingCenter(@PathVariable Long id) {
        log.info("Received request to activate training center with ID: {}", id);

        try {
            TrainingCenterDTO activatedCenter = trainingCenterService.activateTrainingCenter(id);
            ApiResponse<TrainingCenterDTO> response = ApiResponse.success(
                    "Training center activated successfully",
                    activatedCenter
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in activateTrainingCenter: {}", e.getMessage());
            throw e;
        }
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<TrainingCenterDTO>> deactivateTrainingCenter(@PathVariable Long id) {
        log.info("Received request to deactivate training center with ID: {}", id);

        try {
            TrainingCenterDTO deactivatedCenter = trainingCenterService.deactivateTrainingCenter(id);
            ApiResponse<TrainingCenterDTO> response = ApiResponse.success(
                    "Training center deactivated successfully",
                    deactivatedCenter
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in deactivateTrainingCenter: {}", e.getMessage());
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTrainingCenter(@PathVariable Long id) {
        log.info("Received request to delete training center with ID: {}", id);

        try {
            trainingCenterService.deleteTrainingCenter(id);
            ApiResponse<Void> response = ApiResponse.success(
                    "Training center deleted successfully",
                    null
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in deleteTrainingCenter: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/courses")
    public ResponseEntity<ApiResponse<List<String>>> getAllOfferedCourses() {
        log.info("Received request to get all offered courses");

        try {
            List<String> courses = trainingCenterService.getAllOfferedCourses();
            ApiResponse<List<String>> response = ApiResponse.success(
                    "All offered courses retrieved successfully",
                    courses
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error in getAllOfferedCourses: {}", e.getMessage());
            throw e;
        }
    }
}
