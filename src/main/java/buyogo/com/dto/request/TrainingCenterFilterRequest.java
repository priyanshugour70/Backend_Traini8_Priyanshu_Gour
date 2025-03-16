package buyogo.com.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingCenterFilterRequest {
    private String centerName;
    private String city;
    private String state;
    private String course;
    private Integer minCapacity;
    private Integer maxCapacity;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate createdAfter;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate createdBefore;

    private Boolean active;
}
