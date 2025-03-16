package buyogo.com.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingCenterUpdateDTO {

    @NotBlank(message = "Center name is required")
    @Size(max = 40, message = "Center name must be less than 40 characters")
    private String centerName;

    @Valid
    @NotNull(message = "Address details are required")
    private AddressDTO address;

    @NotNull(message = "Student capacity is required")
    @Min(value = 1, message = "Student capacity must be at least 1")
    private Integer studentCapacity;

    @NotEmpty(message = "At least one course must be offered")
    private List<String> coursesOffered;

    @Email(message = "Invalid email format")
    private String contactEmail;

    @NotBlank(message = "Contact phone is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number format")
    private String contactPhone;
}
