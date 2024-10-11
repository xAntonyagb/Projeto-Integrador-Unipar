package br.unipar.assetinsight.infra.security;

import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.repositories.UsuarioRepository;
import br.unipar.assetinsight.utils.DataUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Filtro de segurança que intercepta todas as requisições para resgatar o usuario autenticado e colocar no contexto do spring security.
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository userRepository;
    private static final Logger LOGGER = Logger.getLogger(SecurityFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var token = this.getToken(request); // Pega o token da requisição

            if (token == null) {
                throw new Exception("Nenhum token foi retornado da requisição. Ignorando a autenticação...");
            }

            var subject = tokenService.getSubjectByToken(token); // Decodifica o token utilizando a secret para pegar o username

            //Atualizar data de login
            UsuarioEntity user = userRepository.findEntityByUsername(subject)
                    .orElseThrow(() -> new SecurityException("Usuário ["+ subject +"] não pode ser encontrado."));;
            user.setDtLogin(DataUtils.getNow());
            userRepository.save(user);

            //Pega os dados de autentificação do usuario
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            // Guardando o usuário autenticado no contexto do Spring Security - utilizado para validações de autorização
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch (Exception e) {
            LOGGER.warning(e.getMessage());
        }

        filterChain.doFilter(request, response); // Vai pro prox filtro
    }


    private String getToken(HttpServletRequest request) throws Exception {
        //No header da requisição, caça pelo Authorization
        var token = request.getHeader("Authorization");

        //Validações da requisição
        if (token == null || token.isEmpty()) {
            return null;
        }
        else if(!token.startsWith("Bearer ")) {
            LOGGER.info("O token informado não é do tipo Bearer.");
        }

        //Retorna o token convertido da request
        return token.replace("Bearer ", "");
    }
}
