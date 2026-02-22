package br.edu.ufop.invest.domain;

import br.edu.ufop.invest.enums.AccessLevel;
import lombok.*;
import java.util.UUID;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscriber {
    private UUID uid;
    private String fullName;
    private String contactEmail;
    private String secretKey;
    private AccessLevel level;
}