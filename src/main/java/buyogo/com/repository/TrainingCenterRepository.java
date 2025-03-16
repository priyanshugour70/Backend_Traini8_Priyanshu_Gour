// src/main/java/buyogo/com/repository/TrainingCenterRepository.java
package buyogo.com.repository;

import buyogo.com.entity.TrainingCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingCenterRepository extends JpaRepository<TrainingCenter, Long>, JpaSpecificationExecutor<TrainingCenter> {

    boolean existsByCenterCode(String centerCode);

    Optional<TrainingCenter> findByCenterCode(String centerCode);

    Page<TrainingCenter> findByAddressCityIgnoreCase(String city, Pageable pageable);

    @Query("SELECT tc FROM TrainingCenter tc WHERE LOWER(tc.centerName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(tc.centerCode) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(tc.address.city) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(tc.address.state) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<TrainingCenter> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT tc FROM TrainingCenter tc JOIN tc.coursesOffered course " +
            "WHERE LOWER(course) LIKE LOWER(CONCAT('%', :course, '%'))")
    Page<TrainingCenter> findByCourseOffered(@Param("course") String course, Pageable pageable);

    List<TrainingCenter> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    Page<TrainingCenter> findByActive(Boolean active, Pageable pageable);

    @Query("SELECT tc FROM TrainingCenter tc WHERE tc.studentCapacity >= :minCapacity")
    Page<TrainingCenter> findByMinCapacity(@Param("minCapacity") Integer minCapacity, Pageable pageable);

    @Query("SELECT tc FROM TrainingCenter tc WHERE tc.studentCapacity <= :maxCapacity")
    Page<TrainingCenter> findByMaxCapacity(@Param("maxCapacity") Integer maxCapacity, Pageable pageable);

    @Query("SELECT tc FROM TrainingCenter tc " +
            "WHERE tc.studentCapacity >= :minCapacity AND tc.studentCapacity <= :maxCapacity")
    Page<TrainingCenter> findByCapacityRange(
            @Param("minCapacity") Integer minCapacity,
            @Param("maxCapacity") Integer maxCapacity,
            Pageable pageable);
}
