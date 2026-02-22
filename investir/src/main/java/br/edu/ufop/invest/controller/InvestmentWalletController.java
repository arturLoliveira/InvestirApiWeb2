package br.edu.ufop.invest.controller;

import br.edu.ufop.invest.domain.AssetWallet;
import br.edu.ufop.invest.dto.WalletRegistrationDTO;
import br.edu.ufop.invest.service.WalletExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class InvestmentWalletController {

    private final WalletExecutionService walletService;

    @PostMapping("/open")
    public ResponseEntity<AssetWallet> createWallet(@RequestBody WalletRegistrationDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(walletService.setupWallet(request));
    }

    @PutMapping("/sync/{walletId}")
    public ResponseEntity<AssetWallet> updateWallet(@PathVariable UUID walletId, @RequestBody WalletRegistrationDTO data) {
        return ResponseEntity.ok(walletService.refreshWallet(walletId, data));
    }
}