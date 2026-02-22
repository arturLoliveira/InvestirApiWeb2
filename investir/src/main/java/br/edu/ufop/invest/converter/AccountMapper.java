package br.edu.ufop.invest.converter;

import br.edu.ufop.invest.domain.Subscriber;
import br.edu.ufop.invest.dto.AccountProfileDTO;
import br.edu.ufop.invest.entity.SubscriberEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public SubscriberEntity toEntityFromProfile(AccountProfileDTO dto) {
        var entity = new SubscriberEntity();
        entity.setFullName(dto.fullName());
        entity.setContactEmail(dto.contactEmail());
        entity.setSecretKey(dto.secretKey());
        entity.setLevel(dto.accessLevel());
        return entity;
    }

    public Subscriber toDomain(SubscriberEntity entity) {
        return Subscriber.builder()
                .uid(entity.getUid())
                .fullName(entity.getFullName())
                .contactEmail(entity.getContactEmail())
                .level(entity.getLevel())
                .build();
    }
}