package br.unipar.assetinsight.security;

import br.unipar.assetinsight.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Para consultar de forma automática no banco de dados toda vez que um usuário tenta se autenticar na aplicação.
 */
@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private UsuarioRepository userRepository;

    /**
     * Consulta no banco de dados e retorna qual é o usuário que está se autentificando através do seu username.
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado."));
    }
}
