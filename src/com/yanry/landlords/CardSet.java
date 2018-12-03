package com.yanry.landlords;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: yanry
 * @Date: 2018/12/2 10:25
 */
public class CardSet {
    public static CardSet getFullSet() {
        CardSet cardSet = new CardSet();
        for (int i = 0; i < Card.CARD_VISUALS.length(); i++) {
            Card card = Card.get(Card.CARD_VISUALS.charAt(i));
            for (int j = 0; j < 4; j++) {
                cardSet.add(card);
            }
        }
        return cardSet;
    }

    public static CardSet get(String visuals) {
        CardSet cardSet = new CardSet();
        for (int i = 0; i < visuals.length(); i++) {
            cardSet.add(Card.get(visuals.charAt(i)));
        }
        return cardSet;
    }

    private List<Card> cards;
    private int cardCount;

    public CardSet(Card... cards) {
        this.cards = new LinkedList<>();
        this.cardCount = cards.length;
        for (Card card : cards) {
            this.cards.add(card);
        }
    }

    public CardSet(int cardCount) {
        this.cards = new LinkedList<>();
        this.cardCount = cardCount;
    }

    private CardSet() {
        this.cards = new LinkedList<>();
    }

    public CardSet dispatch(Card... cards) {
        CardSet cardSet = new CardSet();
        for (Card card : cards) {
            cardSet.cards.add(card);
            this.cards.remove(card);
            this.cardCount--;
        }
        cardSet.cardCount = cards.length;
        return cardSet;
    }

    public void transfer(CardSet cardSetToTransit, CardSet cardSetToTransitTo) {
        for (Card card : cardSetToTransit.cards) {
            if (this.cards.remove(card)) {
                this.cardCount--;
            }
            cardSetToTransitTo.cards.add(card);
            cardSetToTransitTo.cardCount++;
        }
    }

    public void add(Card card) {
        cards.add(card);
        cardCount++;
    }

    public void add(CardSet cardSet, boolean addCardCount) {
        cards.addAll(cardSet.cards);
        if (addCardCount) {
            cardCount += cardSet.cards.size();
        }
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public String toString() {
        Collections.sort(cards);
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : cards) {
            stringBuilder.append(card);
        }
        for (int i = 0; i < cardCount - cards.size(); i++) {
            stringBuilder.append('?');
        }
        return stringBuilder.toString();
    }
}
