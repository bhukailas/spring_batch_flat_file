package com.example.CifFileProcessor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CardDataProcessor implements ItemProcessor<Card, Card> {
    private final Map<String, Card> cardDataMap = new HashMap<>();

    @Override
    public Card process(Card item) {
        Card prevCard = null;
        if (item.getRecordType().equals("type1")) {
            prevCard = cardDataMap.values().stream().findFirst().orElse(null);
            cardDataMap.clear();
        }
        cardDataMap.putIfAbsent(item.getCardNumber(), item);
        Card card = cardDataMap.get(item.getCardNumber());
        if ("type1".equals(item.getRecordType())) {
            card.setExpirationDate(item.getExpirationDate());
        } else if ("type2".equals(item.getRecordType())) {
            card.setCreditLimit(item.getCreditLimit());
        }
        return prevCard;
    }
    public List<Card> getCombinedRecords() {
        return List.copyOf(cardDataMap.values());
    }
}
