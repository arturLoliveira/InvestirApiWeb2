package br.edu.ufop.invest.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ufop.invest.enums.CategoryType;
import br.edu.ufop.invest.entity.HoldingEntity;

public interface HoldingRepository extends JpaRepository<HoldingEntity, UUID> {
    List<HoldingEntity> findByCategory(CategoryType category);
    List<HoldingEntity> findAll();
    Optional<HoldingEntity> findByUid(UUID uid);
    void deleteByUid(UUID uid);
}