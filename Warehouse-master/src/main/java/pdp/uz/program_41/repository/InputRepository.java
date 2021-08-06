package pdp.uz.program_41.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pdp.uz.program_41.entity.Input;
import pdp.uz.program_41.entity.Measurement;

public interface InputRepository extends JpaRepository<Input, Integer> {
boolean existsInputByFactureNumber(Integer factureNumber);
boolean existsInputByCode(String code);

@Query(value =  "select count(*) > 0 from input", nativeQuery =true)
    boolean existsInput();



}
