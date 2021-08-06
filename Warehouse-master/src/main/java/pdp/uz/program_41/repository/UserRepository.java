package pdp.uz.program_41.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.program_41.entity.Measurement;
import pdp.uz.program_41.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsUserByPhoneNumberAndActive(String phoneNumber, boolean active);
    boolean existsUserByCodeAndActive(String code, boolean active);
boolean existsUserByActive(boolean active);
Page<User> getUserByActive(boolean active, Pageable pageable);
boolean existsUserByIdAndActive(Integer id, boolean active);

}
