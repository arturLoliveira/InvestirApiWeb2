package br.edu.ufop.invest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufop.invest.entity.WalletEntity;
import java.util.UUID;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, UUID> {
    Optional<WalletEntity> findByOwnerUid(UUID ownerUid);
}