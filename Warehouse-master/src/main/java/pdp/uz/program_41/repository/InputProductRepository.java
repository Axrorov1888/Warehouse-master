package pdp.uz.program_41.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pdp.uz.program_41.entity.InputProduct;
import pdp.uz.program_41.entity.Measurement;

import java.util.List;

public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {
boolean existsInputProductByProductIdAndInputId(Integer product_id, Integer input_id);
boolean existsInputProductByInputId(Integer input_id);

@Query(value = "select count(*) > 0 from input_product", nativeQuery =true)
    boolean existsInputProduct();

Page<InputProduct> getInputProductByInputId(Integer input_id, Pageable pageable);
}
