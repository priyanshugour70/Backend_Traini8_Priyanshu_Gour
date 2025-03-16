package buyogo.com.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "training_centers", indexes = {
        @Index(name = "idx_center_code", columnList = "centerCode", unique = true),
        @Index(name = "idx_center_name", columnList = "centerName"),
        @Index(name = "idx_city", columnList = "address_city")
})
public class TrainingCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Center name is required")
    @Size(max = 40, message = "Center name must be less than 40 characters")
    @Column(nullable = false, length = 40)
    private String centerName;

    @NotBlank(message = "Center code is required")
    @Size(min = 12, max = 12, message = "Center code must be exactly 12 characters")
    @Pattern(regexp = "^[A-Za-z0-9]{12}$", message = "Center code must be alphanumeric")
    @Column(nullable = false, length = 12, unique = true)
    private String centerCode;

    @Valid
    @Embedded
    private Address address;

    @Min(value = 1, message = "Student capacity must be at least 1")
    @Column(nullable = false)
    private Integer studentCapacity;

    @ElementCollection
    @CollectionTable(name = "center_courses",
            joinColumns = @JoinColumn(name = "center_id"))
    @Column(name = "course_name", nullable = false)
    private List<String> coursesOffered;

    @Column(nullable = false)
    private Long createdOn;

    @Email(message = "Invalid email format")
    private String contactEmail;

    @NotBlank(message = "Contact phone is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number format")
    @Column(nullable = false)
    private String contactPhone;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    @Builder.Default
    private Boolean active = true;
}
