package br.unipar.assetinsight.security;

import br.unipar.assetinsight.entities.RolesEntity;
import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.enums.PermissoesEnum;
import br.unipar.assetinsight.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;
import java.util.logging.Logger;

@Configuration
public class AdminUserConfig implements CommandLineRunner {
    private static final Logger LOGGER = Logger.getLogger(AdminUserConfig.class.getName());

    private UsuarioRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${assetinsight.user.super.username}")
    private String username;
    @Value("${assetinsight.user.super.password}")
    private String password;

    public AdminUserConfig(UsuarioRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        RolesEntity role = new RolesEntity();
        role.setPermisao(PermissoesEnum.SUPER);
        role.setId(PermissoesEnum.SUPER.getId());

        boolean isUserCadastrado = userRepository.findByListRolesContaining(role).isEmpty();

        if (isUserCadastrado) {
            LOGGER.info("Usuario Super já existe, pulando cadastro...");
        } else {
            for (String key : System.getProperties().stringPropertyNames()) {
                if (key.equals("PASSWORD_SUPER") || key.equals("USERNAME_SUPER")) {
                    System.out.println(key + ": " + System.getProperty(key));
                }
            }

            LOGGER.info("Cadastrando usuario Super...");
            UsuarioEntity user = new UsuarioEntity();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            List<RolesEntity> roles = List.of(role);
            user.setListRoles(roles);
            userRepository.save(user);
            System.out.println("Usuario Super cadastrado com sucesso!");
        }
    }

}