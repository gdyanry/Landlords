package com.yanry.landlords;

public class OfferRecord extends Record {
    private CardSet cards;

    public OfferRecord(Role who, CardSet cards) {
        super(who);
        this.cards = cards;
    }

    public CardSet getCards() {
        return cards;
    }
}
