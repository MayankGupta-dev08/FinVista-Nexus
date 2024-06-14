package dev.mayankg.cards.mapper;

import dev.mayankg.cards.dto.CardsDto;
import dev.mayankg.cards.entity.Cards;

public final class CardsMapper {

    private CardsMapper() {
        // private instance
    }

    public static Cards mapToCards(CardsDto cardsDto) {
        return mapToCards(cardsDto, new Cards());
    }

    public static Cards mapToCards(CardsDto cardsDto, Cards cards) {
        cards.setCardNumber(cardsDto.getCardNumber());
        cards.setCardType(cardsDto.getCardType());
        cards.setMobileNumber(cardsDto.getMobileNumber());
        cards.setTotalLimit(cardsDto.getTotalLimit());
        cards.setAvailableAmount(cardsDto.getAvailableAmount());
        cards.setAmountUsed(cardsDto.getAmountUsed());
        return cards;
    }

    public static CardsDto mapToCardsDto(Cards cards) {
        return mapToCardsDto(cards, new CardsDto());
    }

    public static CardsDto mapToCardsDto(Cards cards, CardsDto cardsDto) {
        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setMobileNumber(cards.getMobileNumber());
        cardsDto.setTotalLimit(cards.getTotalLimit());
        cardsDto.setAvailableAmount(cards.getAvailableAmount());
        cardsDto.setAmountUsed(cards.getAmountUsed());
        return cardsDto;
    }
}