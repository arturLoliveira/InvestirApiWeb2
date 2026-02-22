package br.edu.ufop.invest.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import br.edu.ufop.invest.enums.CategoryType;
import java.util.Map;

public record WalletRegistrationDTO(
        UUID ownerId,
        BigDecimal aggregatedValue,
        Map<CategoryType, BigDecimal> distributionMap,
        Long totalQuantity,
        List<HoldingInputDTO> holdings
) {}