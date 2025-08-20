package CC.CleanCircuit.services;

import CC.CleanCircuit.entities.RefreshTokenEntity;
import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.repositories.RefreshTokenRepository;
import CC.CleanCircuit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${jwt.refresh.expiration}") // Ex: 7 dias
    private Long refreshExpirationMs;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public RefreshTokenEntity createRefreshToken(UserEntity user) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(new Date(System.currentTimeMillis() + refreshExpirationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        return refreshTokenRepository.save(refreshToken);
    }

    public boolean isValid(RefreshTokenEntity token) {
        return token.getExpiryDate().after(new Date());
    }

    public Optional<RefreshTokenEntity> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public void deleteByUser(UserEntity user) {
        refreshTokenRepository.deleteByUser(user);
    }

}
