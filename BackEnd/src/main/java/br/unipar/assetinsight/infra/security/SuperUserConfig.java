package br.unipar.assetinsight.infra.security;

import br.unipar.assetinsight.dtos.requests.CadastroRequest;
import br.unipar.assetinsight.enums.PermissoesEnum;
import br.unipar.assetinsight.exceptions.ValidationException;
import br.unipar.assetinsight.service.AuthenticationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.logging.Logger;

@Configuration
public class SuperUserConfig implements CommandLineRunner {
    private static final Logger LOGGER = Logger.getLogger(SuperUserConfig.class.getName());

    @Autowired
    private AuthenticationService authService;

    @Value("${assetinsight.user.super.username}")
    private String username;
    @Value("${assetinsight.user.super.password}")
    private String password;

    @Override
    @Transactional
    public void run(String... args) {
        List<PermissoesEnum> roleSuper = List.of(PermissoesEnum.SUPER);

        LOGGER.info("Cadastrando usuario Super...");
        CadastroRequest novoUsuario = new CadastroRequest(username, password, roleSuper);
        try {
            authService.cadastrarUsuario(novoUsuario);
        } catch (ValidationException e) {
            LOGGER.warning("Usuario Super já existe.");
        }
        LOGGER.info("Usuario Super cadastrado com sucesso!");
    }

}