package buyogo.com.repository.spec;

import buyogo.com.dto.request.TrainingCenterFilterRequest;
import buyogo.com.entity.TrainingCenter;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TrainingCenterSpecifications {

    public static Specification<TrainingCenter> getFilterSpecification(TrainingCenterFilterRequest filter) {
        return Specification
                .where(withCenterName(filter.getCenterName()))
                .and(withCity(filter.getCity()))
                .and(withState(filter.getState()))
                .and(withCourse(filter.getCourse()))
                .and(withMinCapacity(filter.getMinCapacity()))
                .and(withMaxCapacity(filter.getMaxCapacity()))
                .and(withCreatedAfter(filter.getCreatedAfter()))
                .and(withCreatedBefore(filter.getCreatedBefore()))
                .and(withActiveStatus(filter.getActive()));
    }

    private static Specification<TrainingCenter> withCenterName(String centerName) {
        if (!StringUtils.hasText(centerName)) return null;

        return (root, query, cb) ->
                cb.like(cb.lower(root.get("centerName")), "%" + centerName.toLowerCase() + "%");
    }

    private static Specification<TrainingCenter> withCity(String city) {
        if (!StringUtils.hasText(city)) return null;

        return (root, query, cb) ->
                cb.like(cb.lower(root.get("address").get("city")), "%" + city.toLowerCase() + "%");
    }

    private static Specification<TrainingCenter> withState(String state) {
        if (!StringUtils.hasText(state)) return null;

        return (root, query, cb) ->
                cb.like(cb.lower(root.get("address").get("state")), "%" + state.toLowerCase() + "%");
    }

    private static Specification<TrainingCenter> withCourse(String course) {
        if (!StringUtils.hasText(course)) return null;

        return (root, query, cb) -> {
            Join<TrainingCenter, String> coursesJoin = root.join("coursesOffered");
            return cb.like(cb.lower(coursesJoin.as(String.class)), "%" + course.toLowerCase() + "%");
        };
    }

    private static Specification<TrainingCenter> withMinCapacity(Integer minCapacity) {
        if (minCapacity == null) return null;

        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("studentCapacity"), minCapacity);
    }

    private static Specification<TrainingCenter> withMaxCapacity(Integer maxCapacity) {
        if (maxCapacity == null) return null;

        return (root, query, cb) ->
                cb.lessThanOrEqualTo(root.get("studentCapacity"), maxCapacity);
    }

    private static Specification<TrainingCenter> withCreatedAfter(LocalDate createdAfter) {
        if (createdAfter == null) return null;

        LocalDateTime startOfDay = createdAfter.atStartOfDay();
        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("createdAt"), startOfDay);
    }

    private static Specification<TrainingCenter> withCreatedBefore(LocalDate createdBefore) {
        if (createdBefore == null) return null;

        LocalDateTime endOfDay = createdBefore.atTime(LocalTime.MAX);
        return (root, query, cb) ->
                cb.lessThanOrEqualTo(root.get("createdAt"), endOfDay);
    }

    private static Specification<TrainingCenter> withActiveStatus(Boolean active) {
        if (active == null) return null;

        return (root, query, cb) ->
                cb.equal(root.get("active"), active);
    }
}
