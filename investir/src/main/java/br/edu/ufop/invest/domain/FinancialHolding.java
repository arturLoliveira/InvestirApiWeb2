package br.edu.ufop.invest.domain;

import br.edu.ufop.invest.enums.CategoryType;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialHolding {
    private CategoryType assetCategory;
    private String ticker;
    private Integer volume;
    private BigDecimal acquisitionCost;
    private LocalDate entryDate;
}