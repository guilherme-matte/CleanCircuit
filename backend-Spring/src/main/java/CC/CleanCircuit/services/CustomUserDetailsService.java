package CC.CleanCircuit.services;

import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com email: " + email);
        }

        // Converte sua entidade para o UserDetails que o Spring entende
        return User.builder()
                .username(user.getEmail())       // login será o email
                .password(user.getSenha())       // senha do banco
                .roles("USER")                   // pode trocar se tiver roles diferentes
                .build();
    }
}

