package pdp.uz.program_41.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.program_41.entity.Measurement;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    boolean existsMeasurementByActive(boolean active);
    Page<Measurement> getMeasurementByActive(boolean active, Pageable pageable);
    boolean existsMeasurementByIdAndActive(Integer id, boolean active);
    boolean existsMeasurementByNameAndActive(String name, boolean active);
}
