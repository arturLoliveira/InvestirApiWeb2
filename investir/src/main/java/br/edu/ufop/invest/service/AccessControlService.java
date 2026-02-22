package br.edu.ufop.invest.service;

import br.edu.ufop.invest.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccessControlService implements UserDetailsService {

    private final SubscriberRepository subscriberRepo;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        return subscriberRepo.findByContactEmail(identifier)
                .map(acc -> (UserDetails) User.builder()
                        .username(acc.getContactEmail())
                        .password(acc.getSecretKey())
                        .authorities("ROLE_" + acc.getLevel().name())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Perfil não localizado."));
    }
}