package br.edu.ufop.invest.controller;

import br.edu.ufop.invest.domain.Subscriber;
import br.edu.ufop.invest.dto.AccountProfileDTO;
import br.edu.ufop.invest.service.AccountSubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class ClientAccountController {

    private final AccountSubscriberService accountService;

    @PostMapping("/register")
    public ResponseEntity<Subscriber> signup(@RequestBody AccountProfileDTO profileData) {
        return new ResponseEntity<>(accountService.registerAccount(profileData), HttpStatus.CREATED);
    }

    @GetMapping("/directory")
    public ResponseEntity<List<Subscriber>> listAll() {
        return ResponseEntity.ok(accountService.listAllProfiles());
    }
}