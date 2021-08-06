package pdp.uz.program_41.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.program_41.entity.Measurement;
import pdp.uz.program_41.entity.Warehouse;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
boolean existsWarehouseByNameAndActive(String name, boolean active);
boolean existsWarehouseByActive(boolean active);
Page<Warehouse> getWarehousesByActive(boolean active, Pageable pageable);
boolean existsWarehouseByIdAndActive(Integer id, boolean active);


}
