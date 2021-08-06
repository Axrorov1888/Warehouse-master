package pdp.uz.program_41.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.program_41.entity.Client;
import pdp.uz.program_41.entity.Measurement;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
boolean existsClientByPhoneNumberAndActive(String phoneNumber, boolean active);
boolean existsClientByActive(boolean active);
boolean existsClientByIdAndActive(Integer id, boolean active);
Page<Client> getClientByActive(boolean active, Pageable pageable);


}
