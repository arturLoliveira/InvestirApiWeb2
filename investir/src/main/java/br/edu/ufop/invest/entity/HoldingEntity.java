package br.edu.ufop.invest.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

import br.edu.ufop.invest.enums.CategoryType;
import br.edu.ufop.invest.entity.base.PersistenceTrace;


@Entity
@Table(name = "financial_holdings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoldingEntity extends PersistenceTrace {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "holding_uid", nullable = false)
    private UUID uid;

    @Enumerated(EnumType.STRING)
    @Column(name = "asset_category")
    private CategoryType category;

    @Column(name = "asset_ticker")
    private String ticker;

    @Column(name = "asset_volume")
    private Integer volume;

    @Column(name = "acquisition_cost")
    private BigDecimal price;

    @Column(name = "operation_date")
    private LocalDate createdAt;
}