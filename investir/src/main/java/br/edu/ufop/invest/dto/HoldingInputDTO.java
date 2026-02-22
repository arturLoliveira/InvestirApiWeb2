package br.edu.ufop.invest.dto;

import java.math.BigDecimal;
import br.edu.ufop.invest.enums.CategoryType;
import java.time.LocalDate;

public record HoldingInputDTO(
        CategoryType assetCategory,
        String ticker,
        Integer volume,
        BigDecimal acquisitionCost,
        LocalDate entryDate
) {}