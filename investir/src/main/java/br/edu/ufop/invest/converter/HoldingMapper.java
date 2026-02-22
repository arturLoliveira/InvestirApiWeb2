package br.edu.ufop.invest.converter;

import org.springframework.stereotype.Component;
import br.edu.ufop.invest.entity.HoldingEntity;
import br.edu.ufop.invest.domain.FinancialHolding;
import br.edu.ufop.invest.dto.HoldingInputDTO;

@Component
public class HoldingMapper {
    public FinancialHolding toModel(HoldingEntity entity) {
        if (entity == null) return null;
        return new FinancialHolding(entity.getCategory(), entity.getTicker(), entity.getVolume(), entity.getPrice(), entity.getCreatedAt());
    }

    public HoldingEntity toEntity(FinancialHolding model) {
        if (model == null) return null;
        HoldingEntity entity = new HoldingEntity();
        entity.setCategory(model.getAssetCategory());
        entity.setTicker(model.getTicker());
        entity.setVolume(model.getVolume());
        entity.setPrice(model.getAcquisitionCost());
        entity.setCreatedAt(model.getEntryDate());
        return entity;
    }

    public FinancialHolding toDomainFromDTO(HoldingInputDTO dto) {
        if (dto == null) return null;
        return new FinancialHolding(dto.assetCategory(), dto.ticker(), dto.volume(), dto.acquisitionCost(), dto.entryDate());
    }
}