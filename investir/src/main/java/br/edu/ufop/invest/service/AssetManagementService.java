package br.edu.ufop.invest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.edu.ufop.invest.repository.HoldingRepository;
import br.edu.ufop.invest.domain.FinancialHolding;
import br.edu.ufop.invest.dto.HoldingInputDTO;
import br.edu.ufop.invest.converter.HoldingMapper;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetManagementService {
    private final HoldingRepository holdingRepo;
    private final HoldingMapper mapper;

    public FinancialHolding processNewEntry(HoldingInputDTO data) {
        var model = new FinancialHolding(
                data.assetCategory(), data.ticker(), data.volume(), data.acquisitionCost(), data.entryDate()
        );
        var saved = holdingRepo.save(mapper.toEntity(model));
        return mapper.toModel(saved);
    }

    public List<FinancialHolding> fetchAll() {
        return holdingRepo.findAll().stream().map(mapper::toModel).collect(Collectors.toList());
    }
}