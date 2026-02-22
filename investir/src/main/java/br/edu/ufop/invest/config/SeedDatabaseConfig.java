package br.edu.ufop.invest.config;

import br.edu.ufop.invest.entity.SubscriberEntity;
import br.edu.ufop.invest.repository.SubscriberRepository;
import br.edu.ufop.invest.enums.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class SeedDatabaseConfig {

    @Bean
    CommandLineRunner provisionAdmin(SubscriberRepository userRepo, PasswordEncoder passEncoder) {
        return args -> {
            String primaryAdmin = "master@invest.com";

            if (userRepo.findByContactEmail(primaryAdmin).isEmpty()) {

                var adminAccount = SubscriberEntity.builder()
                        .fullName("Sistema Master")
                        .contactEmail(primaryAdmin)
                        .secretKey(passEncoder.encode("invest_2026_secure"))
                        .level(AccessLevel.ADMINISTRATOR)
                        .isEnabled(true)
                        .build();

                userRepo.save(adminAccount);
                log.info("Conta administrativa provisionada com sucesso: {}", primaryAdmin);
            } else {
                log.info("O usuário administrador já reside na base de dados.");
            }
        };
    }
}