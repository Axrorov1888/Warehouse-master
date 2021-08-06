package pdp.uz.program_41.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pdp.uz.program_41.entity.Measurement;
import pdp.uz.program_41.entity.Output;

public interface OutputRepository extends JpaRepository<Output, Integer> {
boolean existsOutputByCode(String code);
boolean existsOutputByFactureNumber(Integer factureNumber);

@Query(value = "select count(*) > 0 from output", nativeQuery =true)
    boolean existsOutput();

}
