package br.edu.ufop.invest.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import java.math.BigDecimal;
import java.util.Map;
import br.edu.ufop.invest.enums.CategoryType;
import java.util.List;

public record WalletOverviewDTO(
        UUID walletId,

        @NotNull(message = "O titular da conta é obrigatório")
        AccountProfileDTO owner,

        @NotNull(message = "A listagem de ativos é obrigatória")
        List<HoldingInputDTO> holdings,

        @NotNull(message = "O valor total agregado é obrigatório")
        BigDecimal aggregatedValue,

        @NotNull(message = "O mapa de distribuição por categoria é obrigatório")
        Map<CategoryType, BigDecimal> distributionMap,

        @NotNull(message = "O volume total de ativos é obrigatório")
        Long totalQuantity
) {
}