package com.yanry.landlords.console;

import com.yanry.landlords.CardSet;
import com.yanry.landlords.LandLordsGame;
import com.yanry.landlords.Role;
import lib.common.util.console.query.ConsoleQuery;
import lib.common.util.console.query.Select;

public class ConsoleBoard extends LandLordsGame {
    private ConsoleQuery<Role> roleQuery;
    private Select<Boolean> booleanSelect;
    private ConsoleQuery<CardSet> cardSetQuery;

    public ConsoleBoard() {
        roleQuery = new Select<>(Role.values());
        booleanSelect = new Select<>(false, true);
        cardSetQuery = new CardSetQuery();
    }

    @Override
    protected Role getStarter() {
        return roleQuery.getValue("谁先叫地主");
    }

    @Override
    protected boolean isWillingToCompete(Role role) {
        return booleanSelect.getValue(role + "有没有叫地主");
    }

    @Override
    protected CardSet getOpenCards() {
        return cardSetQuery.getValue("地主翻牌");
    }

    @Override
    protected CardSet getMyCards() {
        return cardSetQuery.getValue("我的牌");
    }

    @Override
    protected CardSet getOfferedCards(Role role) {
        return cardSetQuery.getValue(role + "出牌");
    }

    public static void main(String... args) {
        ConsoleBoard consoleBoard = new ConsoleBoard();
        consoleBoard.start();
    }
}
