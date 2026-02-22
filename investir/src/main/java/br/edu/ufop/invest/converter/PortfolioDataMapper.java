package br.edu.ufop.invest.converter;

import org.springframework.stereotype.Component;
import br.edu.ufop.invest.entity.WalletEntity;
import br.edu.ufop.invest.domain.AssetWallet;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PortfolioDataMapper {

    private final HoldingMapper assetMapper; // Era AssetDataMapper
    private final AccountMapper userMapper;  // Era UserAccountMapper

    public AssetWallet entityToModel(WalletEntity record) {
        if (record == null) return null;

        return AssetWallet.builder() // Certifique-se que o builder está na classe AssetWallet
                .walletId(record.getUid())
                .owner(userMapper.toDomain(record.getOwner()))
                .items(record.getHoldings().stream()
                        .map(assetMapper::toModel)
                        .collect(Collectors.toList()))
                .build();
    }
}