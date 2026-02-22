package br.edu.ufop.invest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

import br.edu.ufop.invest.enums.AccessLevel;
import br.edu.ufop.invest.entity.base.PersistenceTrace;

@Entity
@Table(name = "account_subscribers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriberEntity extends PersistenceTrace {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "subscriber_uid")
    private UUID uid;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "contact_email", nullable = false, unique = true)
    private String contactEmail;

    @Column(name = "access_credential", nullable = false)
    private String secretKey;

    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_level")
    private AccessLevel level;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private WalletEntity wallet;

    @PostLoad
    @PrePersist
    private void initializeStatus() {
        if (this.isEnabled == null) {
            this.isEnabled = true;
        }
    }
}