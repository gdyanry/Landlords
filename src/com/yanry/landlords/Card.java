package com.yanry.landlords;

/**
 * @Author: yanry
 * @Date: 2018/12/2 11:34
 */
public class Card implements Comparable<Card> {
    private int rank;
    private char visual;

    public Card(int rank, char visual) {
        this.rank = rank;
        this.visual = visual;
    }

    @Override
    public int compareTo(Card o) {
        return rank - o.rank;
    }

    @Override
    public String toString() {
        return String.valueOf(visual);
    }
}
