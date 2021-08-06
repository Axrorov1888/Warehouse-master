package pdp.uz.program_41.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.program_41.entity.Measurement;
import pdp.uz.program_41.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
boolean existsProductByNameAndCategoryIdAndActive(String name, Integer categoryId, boolean active);
boolean existsProductByCodeAndActive(String code, boolean active);
boolean existsProductByActive(boolean active);
Page<Product> getProductByActive(boolean active, Pageable pageable);
boolean existsProductByIdAndActive(Integer id, boolean active);

}
