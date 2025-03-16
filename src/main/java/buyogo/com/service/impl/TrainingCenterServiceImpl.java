package buyogo.com.service.impl;

import buyogo.com.dto.TrainingCenterCreateDTO;
import buyogo.com.dto.TrainingCenterDTO;
import buyogo.com.dto.TrainingCenterUpdateDTO;
import buyogo.com.dto.request.TrainingCenterFilterRequest;
import buyogo.com.entity.TrainingCenter;
import buyogo.com.exception.DuplicateResourceException;
import buyogo.com.exception.ResourceNotFoundException;
import buyogo.com.exception.ValidationException;
import buyogo.com.mapper.TrainingCenterMapper;
import buyogo.com.repository.TrainingCenterRepository;
import buyogo.com.repository.spec.TrainingCenterSpecifications;
import buyogo.com.service.TrainingCenterService;
import buyogo.com.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrainingCenterServiceImpl implements TrainingCenterService {

    private final TrainingCenterRepository trainingCenterRepository;
    private final TrainingCenterMapper trainingCenterMapper;
    private final CodeGenerator codeGenerator;

    @Override
    @Transactional
    public TrainingCenterDTO createTrainingCenter(TrainingCenterCreateDTO dto) {
        log.debug("Creating new training center: {}", dto.getCenterName());

        try {
            validateTrainingCenterCreate(dto);

            // Generate a unique code for the training center
            String centerCode;
            do {
                centerCode = codeGenerator.generateCenterCode();
            } while (trainingCenterRepository.existsByCenterCode(centerCode));

            TrainingCenter center = trainingCenterMapper.toEntity(dto);
            center.setCenterCode(centerCode);
            center.setCreatedOn(Instant.now().toEpochMilli());

            TrainingCenter savedCenter = trainingCenterRepository.save(center);
            log.info("Successfully created training center with ID: {}, code: {}", savedCenter.getId(), savedCenter.getCenterCode());

            return trainingCenterMapper.toDto(savedCenter);
        } catch (Exception e) {
            log.error("Error creating training center: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TrainingCenterDTO> getAllTrainingCenters(Pageable pageable) {
        log.debug("Fetching all training centers with pagination: {}", pageable);

        try {
            Page<TrainingCenter> centers = trainingCenterRepository.findAll(pageable);
            log.info("Retrieved {} training centers", centers.getTotalElements());

            return centers.map(trainingCenterMapper::toDto);
        } catch (Exception e) {
            log.error("Error fetching all training centers: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public TrainingCenterDTO getTrainingCenterById(Long id) {
        log.debug("Fetching training center by ID: {}", id);

        try {
            TrainingCenter center = findTrainingCenterById(id);
            log.info("Retrieved training center with ID: {}", id);

            return trainingCenterMapper.toDto(center);
        } catch (Exception e) {
            log.error("Error fetching training center by ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public TrainingCenterDTO getTrainingCenterByCode(String centerCode) {
        log.debug("Fetching training center by code: {}", centerCode);

        try {
            TrainingCenter center = trainingCenterRepository.findByCenterCode(centerCode)
                    .orElseThrow(() -> new ResourceNotFoundException("Training center not found with code: " + centerCode));

            log.info("Retrieved training center with code: {}", centerCode);
            return trainingCenterMapper.toDto(center);
        } catch (Exception e) {
            log.error("Error fetching training center by code {}: {}", centerCode, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TrainingCenterDTO> getTrainingCentersByCity(String city, Pageable pageable) {
        log.debug("Fetching training centers by city: {}", city);

        try {
            Page<TrainingCenter> centers = trainingCenterRepository.findByAddressCityIgnoreCase(city, pageable);
            log.info("Retrieved {} training centers for city: {}", centers.getTotalElements(), city);

            return centers.map(trainingCenterMapper::toDto);
        } catch (Exception e) {
            log.error("Error fetching training centers by city {}: {}", city, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TrainingCenterDTO> searchTrainingCenters(String keyword, Pageable pageable) {
        log.debug("Searching training centers with keyword: {}", keyword);

        try {
            Page<TrainingCenter> centers = trainingCenterRepository.searchByKeyword(keyword, pageable);
            log.info("Found {} training centers matching keyword: {}", centers.getTotalElements(), keyword);

            return centers.map(trainingCenterMapper::toDto);
        } catch (Exception e) {
            log.error("Error searching training centers with keyword {}: {}", keyword, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TrainingCenterDTO> getTrainingCentersByCourse(String course, Pageable pageable) {
        log.debug("Fetching training centers offering course: {}", course);

        try {
            Page<TrainingCenter> centers = trainingCenterRepository.findByCourseOffered(course, pageable);
            log.info("Retrieved {} training centers offering course: {}", centers.getTotalElements(), course);

            return centers.map(trainingCenterMapper::toDto);
        } catch (Exception e) {
            log.error("Error fetching training centers by course {}: {}", course, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TrainingCenterDTO> filterTrainingCenters(TrainingCenterFilterRequest filterRequest, Pageable pageable) {
        log.debug("Filtering training centers with criteria: {}", filterRequest);

        try {
            Page<TrainingCenter> centers = trainingCenterRepository.findAll(
                    TrainingCenterSpecifications.getFilterSpecification(filterRequest),
                    pageable
            );

            log.info("Found {} training centers matching filter criteria", centers.getTotalElements());
            return centers.map(trainingCenterMapper::toDto);
        } catch (Exception e) {
            log.error("Error filtering training centers: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public TrainingCenterDTO updateTrainingCenter(Long id, TrainingCenterUpdateDTO dto) {
        log.debug("Updating training center with ID: {}", id);

        try {
            TrainingCenter existingCenter = findTrainingCenterById(id);

            trainingCenterMapper.updateEntityFromDto(dto, existingCenter);
            TrainingCenter updatedCenter = trainingCenterRepository.save(existingCenter);

            log.info("Successfully updated training center with ID: {}", id);
            return trainingCenterMapper.toDto(updatedCenter);
        } catch (Exception e) {
            log.error("Error updating training center with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public TrainingCenterDTO activateTrainingCenter(Long id) {
        log.debug("Activating training center with ID: {}", id);

        try {
            TrainingCenter center = findTrainingCenterById(id);

            if (Boolean.TRUE.equals(center.getActive())) {
                log.info("Training center with ID: {} is already active", id);
                return trainingCenterMapper.toDto(center);
            }

            center.setActive(true);
            TrainingCenter updatedCenter = trainingCenterRepository.save(center);

            log.info("Successfully activated training center with ID: {}", id);
            return trainingCenterMapper.toDto(updatedCenter);
        } catch (Exception e) {
            log.error("Error activating training center with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public TrainingCenterDTO deactivateTrainingCenter(Long id) {
        log.debug("Deactivating training center with ID: {}", id);

        try {
            TrainingCenter center = findTrainingCenterById(id);

            if (Boolean.FALSE.equals(center.getActive())) {
                log.info("Training center with ID: {} is already inactive", id);
                return trainingCenterMapper.toDto(center);
            }

            center.setActive(false);
            TrainingCenter updatedCenter = trainingCenterRepository.save(center);

            log.info("Successfully deactivated training center with ID: {}", id);
            return trainingCenterMapper.toDto(updatedCenter);
        } catch (Exception e) {
            log.error("Error deactivating training center with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteTrainingCenter(Long id) {
        log.debug("Deleting training center with ID: {}", id);

        try {
            if (!trainingCenterRepository.existsById(id)) {
                throw new ResourceNotFoundException("Training center not found with id: " + id);
            }

            trainingCenterRepository.deleteById(id);
            log.info("Successfully deleted training center with ID: {}", id);
        } catch (Exception e) {
            log.error("Error deleting training center with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllOfferedCourses() {
        log.debug("Fetching all offered courses");

        try {
            List<TrainingCenter> allCenters = trainingCenterRepository.findAll();

            Set<String> uniqueCourses = allCenters.stream()
                    .flatMap(center -> center.getCoursesOffered().stream())
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());

            List<String> courses = uniqueCourses.stream().sorted().collect(Collectors.toList());
            log.info("Retrieved {} unique courses", courses.size());

            return courses;
        } catch (Exception e) {
            log.error("Error fetching all offered courses: {}", e.getMessage(), e);
            throw e;
        }
    }

    // Helper methods

    private TrainingCenter findTrainingCenterById(Long id) {
        return trainingCenterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Training center not found with id: " + id));
    }

    private void validateTrainingCenterCreate(TrainingCenterCreateDTO dto) {
        // Additional validations could be added here beyond what's in the annotations
        if (dto.getCoursesOffered() == null || dto.getCoursesOffered().isEmpty()) {
            throw new ValidationException("At least one course must be offered");
        }

        // Validate that all courses are not empty strings
        boolean hasEmptyCourse = dto.getCoursesOffered().stream()
                .anyMatch(course -> course == null || course.trim().isEmpty());

        if (hasEmptyCourse) {
            throw new ValidationException("Course names cannot be empty");
        }
    }
}
