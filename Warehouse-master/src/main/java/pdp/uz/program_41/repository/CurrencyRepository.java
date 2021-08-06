package pdp.uz.program_41.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.program_41.entity.Currency;
import pdp.uz.program_41.entity.Measurement;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
boolean existsCurrencyByNameAndActive(String name, boolean active);
boolean existsCurrencyByActive(boolean active);
Page<Currency> getCurrencyByActive(boolean active, Pageable pageable);
boolean existsCurrencyByIdAndActive(Integer id, boolean active);
}
