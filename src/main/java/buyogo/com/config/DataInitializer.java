package buyogo.com.config;

import buyogo.com.entity.Address;
import buyogo.com.entity.TrainingCenter;
import buyogo.com.repository.TrainingCenterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
@Profile("dev")
public class DataInitializer {

    private final TrainingCenterRepository trainingCenterRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            log.info("Initializing sample data for development environment");

            if (trainingCenterRepository.count() > 0) {
                log.info("Database already contains data, skipping initialization");
                return;
            }

            // Sample Training Center 1
            TrainingCenter center1 = TrainingCenter.builder()
                    .centerName("Delhi Skills Institute")
                    .centerCode("TC12345DELHI")
                    .address(new Address(
                            "123 Main Street, Connaught Place",
                            "Delhi",
                            "Delhi",
                            "110001"
                    ))
                    .studentCapacity(200)
                    .coursesOffered(Arrays.asList("Computer Science", "Web Development", "Data Science"))
                    .contactEmail("info@delhiskills.com")
                    .contactPhone("9812345678")
                    .createdOn(Instant.now().toEpochMilli())
                    .active(true)
                    .build();

            // Sample Training Center 2
            TrainingCenter center2 = TrainingCenter.builder()
                    .centerName("Mumbai Tech Academy")
                    .centerCode("TC67890MUMBAI")
                    .address(new Address(
                            "456 Marine Drive",
                            "Mumbai",
                            "Maharashtra",
                            "400002"
                    ))
                    .studentCapacity(150)
                    .coursesOffered(Arrays.asList("Mobile App Development", "Cloud Computing", "Cybersecurity"))
                    .contactEmail("info@mumbaitech.com")
                    .contactPhone("9823456789")
                    .createdOn(Instant.now().toEpochMilli())
                    .active(true)
                    .build();

            // Sample Training Center 3
            TrainingCenter center3 = TrainingCenter.builder()
                    .centerName("Bangalore Software Training")
                    .centerCode("TC54321BLORE")
                    .address(new Address(
                            "789 Electronic City",
                            "Bangalore",
                            "Karnataka",
                            "560100"
                    ))
                    .studentCapacity(250)
                    .coursesOffered(Arrays.asList("Java Programming", "Python", "Machine Learning", "DevOps"))
                    .contactEmail("info@bangaloresoftware.com")
                    .contactPhone("9834567890")
                    .createdOn(Instant.now().toEpochMilli())
                    .active(true)
                    .build();

            // Sample Training Center 4
            TrainingCenter center4 = TrainingCenter.builder()
                    .centerName("Chennai Engineering Academy")
                    .centerCode("TC09876CHENAI")
                    .address(new Address(
                            "321 Anna Salai",
                            "Chennai",
                            "Tamil Nadu",
                            "600002"
                    ))
                    .studentCapacity(180)
                    .coursesOffered(Arrays.asList("Mechanical Design", "Civil Engineering", "Electrical Engineering"))
                    .contactEmail("info@chennaiacademy.com")
                    .contactPhone("9845678901")
                    .createdOn(Instant.now().toEpochMilli())
                    .active(true)
                    .build();

            // Sample Training Center 5
            TrainingCenter center5 = TrainingCenter.builder()
                    .centerName("Kolkata Management Institute")
                    .centerCode("TC13579KOLKAT")
                    .address(new Address(
                            "987 Park Street",
                            "Kolkata",
                            "West Bengal",
                            "700016"
                    ))
                    .studentCapacity(120)
                    .coursesOffered(Arrays.asList("Business Administration", "Marketing", "Finance", "Human Resources"))
                    .contactEmail("info@kolkatamanagement.com")
                    .contactPhone("9856789012")
                    .createdOn(Instant.now().toEpochMilli())
                    .active(false) // This one is inactive
                    .build();

            List<TrainingCenter> centers = Arrays.asList(center1, center2, center3, center4, center5);
            trainingCenterRepository.saveAll(centers);

            log.info("Sample data initialized with {} training centers", centers.size());
        };
    }
}
