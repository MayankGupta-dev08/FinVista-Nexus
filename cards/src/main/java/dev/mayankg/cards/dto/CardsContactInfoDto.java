package dev.mayankg.cards.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;

@Getter @Setter
@ConfigurationProperties(prefix = "cards")
public class CardsContactInfoDto {

    private String message;
    private HashMap<String, String> contactDetails;
    private List<String> onCallSupport;

}