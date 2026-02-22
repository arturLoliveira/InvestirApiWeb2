package br.edu.ufop.invest.domain;

import lombok.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import br.edu.ufop.invest.enums.CategoryType;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AssetWallet {
    private UUID walletId;
    private Subscriber owner;
    private BigDecimal aggregatedValue;
    private Map<CategoryType, BigDecimal> distributionMap;
    private List<FinancialHolding> items;
    private Long totalQuantity;

    public AssetWallet(UUID walletId, Subscriber owner, List<FinancialHolding> items) {
        this.walletId = walletId;
        this.owner = owner;
        this.items = items;

        if (this.items == null || this.items.isEmpty()) {
            this.aggregatedValue = BigDecimal.ZERO;
            this.distributionMap = new HashMap<>();
            this.totalQuantity = 0L;
        } else {
            this.aggregatedValue = this.items.stream()
                    .map(FinancialHolding::getAcquisitionCost)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            Map<CategoryType, Long> counts = this.items.stream()
                    .collect(Collectors.groupingBy(FinancialHolding::getAssetCategory,
                            Collectors.summingLong(FinancialHolding::getVolume)));

            this.distributionMap = counts.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> BigDecimal.valueOf(e.getValue())));

            this.totalQuantity = counts.values().stream().reduce(0L, Long::sum);
        }
    }
}