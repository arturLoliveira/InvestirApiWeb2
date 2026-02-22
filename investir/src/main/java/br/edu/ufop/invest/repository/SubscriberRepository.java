package br.edu.ufop.invest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufop.invest.entity.SubscriberEntity;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubscriberRepository extends JpaRepository<SubscriberEntity, UUID> {
    Optional<SubscriberEntity> findByContactEmail(String contactEmail);
}