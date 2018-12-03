package com.yanry.landlords;

import java.util.HashMap;

/**
 * @Author: yanry
 * @Date: 2018/12/2 11:34
 */
public class Card implements Comparable<Card> {
    public static final String CARD_VISUALS = "34567890jqk12xd";
    private static HashMap<Character, Card> cache = new HashMap<>();

    static {
        for (int i = 0; i < CARD_VISUALS.length(); i++) {
            char c = CARD_VISUALS.charAt(i);
            cache.put(c, new Card(i, c));
        }
    }

    public static Card get(char visual) {
        return cache.get(visual);
    }

    private int rank;
    private char visual;

    private Card(int rank, char visual) {
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
