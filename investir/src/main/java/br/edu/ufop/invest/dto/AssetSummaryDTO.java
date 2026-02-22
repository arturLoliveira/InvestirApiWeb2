package br.edu.ufop.invest.dto;

import java.math.BigDecimal;
import br.edu.ufop.invest.enums.CategoryType;
import java.util.Map;


public record AssetSummaryDTO(
        BigDecimal aggregatedValue,
        Map<CategoryType, BigDecimal> distributionMap,
        Long totalQuantity
) {}