package pdp.uz.program_41.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.program_41.entity.Measurement;
import pdp.uz.program_41.entity.Supplier;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
boolean existsSupplierByPhoneNumberAndActive(String phoneNumber, boolean active);
boolean existsSupplierByActive(boolean active);
Page<Supplier> getSupplierByActive(boolean active, Pageable pageable);
boolean existsSupplierByIdAndActive(Integer id, boolean active);


}
