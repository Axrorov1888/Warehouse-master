package pdp.uz.program_41.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pdp.uz.program_41.entity.Measurement;
import pdp.uz.program_41.entity.OutputProduct;

import java.util.List;

public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {
boolean existsOutputProductByProductIdAndOutputId(Integer product_id, Integer output_id);

@Query(value = "select count(*) > 0 from output_product", nativeQuery = true)
    boolean existsOutputProduct();

boolean existsOutputProductByOutputId(Integer output_id);
Page<OutputProduct> getOutputProductByOutputId(Integer output_id, Pageable pageable);

}
