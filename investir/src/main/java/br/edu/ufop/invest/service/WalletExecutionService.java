package br.edu.ufop.invest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

import br.edu.ufop.invest.repository.WalletRepository;
import br.edu.ufop.invest.repository.SubscriberRepository;
import br.edu.ufop.invest.converter.PortfolioDataMapper;
import br.edu.ufop.invest.converter.HoldingMapper;
import br.edu.ufop.invest.entity.WalletEntity;
import br.edu.ufop.invest.domain.AssetWallet;
import br.edu.ufop.invest.dto.WalletRegistrationDTO;
import br.edu.ufop.invest.exception.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class WalletExecutionService {

    private final WalletRepository walletRepo;
    private final SubscriberRepository subscriberRepo;
    private final PortfolioDataMapper walletMapper;
    private final HoldingMapper assetMapper;

    @Transactional
    public AssetWallet setupWallet(WalletRegistrationDTO request) {
        var owner = subscriberRepo.findById(request.ownerId())
                .orElseThrow(() -> new EntityNotFoundException("Titular inexistente."));

        return walletRepo.findByOwnerUid(owner.getUid())
                .map(existing -> refreshWallet(existing.getUid(), request))
                .orElseGet(() -> {
                    var newWallet = new WalletEntity();
                    newWallet.setOwner(owner);
                    newWallet.setAggregatedValue(request.aggregatedValue());
                    newWallet.setHoldings(request.holdings().stream()
                            .map(h -> assetMapper.toEntity(assetMapper.toDomainFromDTO(h)))
                            .collect(Collectors.toList()));
                    return walletMapper.entityToModel(walletRepo.save(newWallet));
                });
    }

    public AssetWallet refreshWallet(UUID walletId, WalletRegistrationDTO data) {
        var wallet = walletRepo.findById(walletId)
                .orElseThrow(() -> new EntityNotFoundException("Carteira não encontrada."));

        wallet.setAggregatedValue(data.aggregatedValue());
        wallet.setTotalQuantity(data.totalQuantity());
        wallet.setHoldings(data.holdings().stream()
                .map(h -> assetMapper.toEntity(assetMapper.toDomainFromDTO(h)))
                .collect(Collectors.toList()));

        return walletMapper.entityToModel(walletRepo.save(wallet));
    }
}