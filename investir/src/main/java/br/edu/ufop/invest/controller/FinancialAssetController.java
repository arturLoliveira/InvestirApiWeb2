package br.edu.ufop.invest.controller;

import br.edu.ufop.invest.domain.FinancialHolding;
import br.edu.ufop.invest.dto.HoldingInputDTO;
import br.edu.ufop.invest.service.AssetManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class FinancialAssetController {
    private final AssetManagementService assetService;

    @PostMapping("/execute")
    public ResponseEntity<FinancialHolding> createEntry(@RequestBody HoldingInputDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assetService.processNewEntry(data));
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<FinancialHolding>> getAll() {
        return ResponseEntity.ok(assetService.fetchAll());
    }
}