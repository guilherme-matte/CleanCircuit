package CC.CleanCircuit.repositories;

import CC.CleanCircuit.entities.RefreshTokenEntity;
import CC.CleanCircuit.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String token);
    void deleteByUser(UserEntity user);
}

