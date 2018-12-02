package com.yanry.landlords;

/**
 * @Author: yanry
 * @Date: 2018/12/2 13:50
 */
public enum  Role {
    Left, Me, Right;

    private Role next;
    private CardSet cards;

    Role() {
        cards = new CardSet(17);
    }

    public Role getNext() {
        return next;
    }

    public void setNext(Role next) {
        this.next = next;
    }

    public CardSet getCards() {
        return cards;
    }

    public CardSet beat(Card... cards) {
        return this.cards.dispatch(cards);
    }
}
