package br.edu.ufop.invest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.edu.ufop.invest.repository.SubscriberRepository;
import br.edu.ufop.invest.domain.Subscriber;
import br.edu.ufop.invest.dto.AccountProfileDTO;
import br.edu.ufop.invest.converter.AccountMapper;
import br.edu.ufop.invest.exception.AccountConflictException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountSubscriberService {
    private final SubscriberRepository subscriberRepo;
    private final AccountMapper accountMapper;

    public Subscriber registerAccount(AccountProfileDTO profile) {
        if (subscriberRepo.findByContactEmail(profile.contactEmail()).isPresent()) {
            throw new AccountConflictException("E-mail já cadastrado.");
        }
        var entity = accountMapper.toEntityFromProfile(profile);
        return accountMapper.toDomain(subscriberRepo.save(entity));
    }

    public List<Subscriber> listAllProfiles() {
        return subscriberRepo.findAll().stream()
                .map(accountMapper::toDomain)
                .collect(Collectors.toList());
    }
}