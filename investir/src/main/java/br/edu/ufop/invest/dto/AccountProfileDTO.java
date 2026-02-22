package br.edu.ufop.invest.dto;

import br.edu.ufop.invest.enums.AccessLevel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import java.util.UUID;


public record AccountProfileDTO (
        UUID uid,

        String fullName,

        @NotNull(message = "O endereço de e-mail é obrigatório para o cadastro")
        @Email(message = "Forneça um formato de e-mail válido")
        String contactEmail,

        @NotNull(message = "A credencial de acesso (senha) não pode estar vazia")
        String secretKey,

        AccessLevel accessLevel,

        WalletOverviewDTO walletOverview
){}