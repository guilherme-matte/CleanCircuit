package CC.CleanCircuit.repositories;

import CC.CleanCircuit.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByCpf(String cpf);
    UserEntity findByEmail(String email);

}
