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
        int i = 0;
        for (int j = '3'; j < '9'; j++) {
            for (int k = 0; k < 4; k++) {
                cardSet.add(new Card(i++ / 4, (char)i));
            }
        }
        for (int j = 0; j < 4; j++) {
            cardSet.add(new Card(7, '0'));
            cardSet.add(new Card(8, 'J'));
            cardSet.add(new Card(9, 'Q'));
            cardSet.add(new Card(10, 'K'));
            cardSet.add(new Card(11, 'A'));
            cardSet.add(new Card(12, '2'));
        }
        cardSet.add(new Card(13, 'X'));
        cardSet.add(new Card(13, 'X'));
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
