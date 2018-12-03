package com.yanry.landlords.console;

import com.yanry.landlords.Card;
import com.yanry.landlords.CardSet;
import lib.common.util.console.query.ConsoleQuery;

public class CardSetQuery extends ConsoleQuery<CardSet> {
    @Override
    protected void appendPromptInfo(StringBuilder promptBuilder) {
    }

    @Override
    protected boolean isValid(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Card.CARD_VISUALS.contains(String.valueOf(input.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected CardSet map(String input) {
        return CardSet.get(input);
    }
}
