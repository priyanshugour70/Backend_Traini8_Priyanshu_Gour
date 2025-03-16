package buyogo.com.service;

import buyogo.com.dto.TrainingCenterCreateDTO;
import buyogo.com.dto.TrainingCenterDTO;
import buyogo.com.dto.TrainingCenterUpdateDTO;
import buyogo.com.dto.request.TrainingCenterFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TrainingCenterService {
    TrainingCenterDTO createTrainingCenter(TrainingCenterCreateDTO dto);

    Page<TrainingCenterDTO> getAllTrainingCenters(Pageable pageable);

    TrainingCenterDTO getTrainingCenterById(Long id);

    TrainingCenterDTO getTrainingCenterByCode(String centerCode);

    Page<TrainingCenterDTO> getTrainingCentersByCity(String city, Pageable pageable);

    Page<TrainingCenterDTO> searchTrainingCenters(String keyword, Pageable pageable);

    Page<TrainingCenterDTO> getTrainingCentersByCourse(String course, Pageable pageable);

    Page<TrainingCenterDTO> filterTrainingCenters(TrainingCenterFilterRequest filterRequest, Pageable pageable);

    TrainingCenterDTO updateTrainingCenter(Long id, TrainingCenterUpdateDTO dto);

    TrainingCenterDTO activateTrainingCenter(Long id);

    TrainingCenterDTO deactivateTrainingCenter(Long id);

    void deleteTrainingCenter(Long id);

    List<String> getAllOfferedCourses();
}
