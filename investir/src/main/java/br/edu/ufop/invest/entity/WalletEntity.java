package br.edu.ufop.invest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.List;
import java.util.UUID;
import br.edu.ufop.invest.enums.CategoryType;
import br.edu.ufop.invest.entity.base.PersistenceTrace;


@Entity
@Table(name = "client_wallets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletEntity extends PersistenceTrace {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "wallet_uid")
    private UUID uid;

    @OneToOne
    @JoinColumn(name = "subscriber_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SubscriberEntity owner;

    @Column(name = "aggregated_value", nullable = false)
    private BigDecimal aggregatedValue;

    @ElementCollection
    @CollectionTable(name = "wallet_distribution", joinColumns = @JoinColumn(name = "wallet_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "category_total")
    private Map<CategoryType, BigDecimal> distributionMap;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "wallet_holdings_map",
            joinColumns = @JoinColumn(name = "wallet_id"),
            inverseJoinColumns = @JoinColumn(name = "holding_id")
    )
    private List<HoldingEntity> holdings;

    @Column(name = "total_asset_count", nullable = false)
    private Long totalQuantity;
}