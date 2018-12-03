package com.yanry.landlords;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: yanry
 * @Date: 2018/12/2 13:39
 */
public abstract class LandLordsGame {
    private List<Record> records;
    private CardSet invisibleCards;

    public LandLordsGame() {
        Role.Left.setNext(Role.Me);
        Role.Me.setNext(Role.Right);
        Role.Right.setNext(Role.Left);
        records = new LinkedList<>();
    }

    public void start() {
        while (true) {
            // init
            records.clear();
            invisibleCards = CardSet.getFullSet();
            Role currentRole = getStarter();
            // compete
            EnumSet<Role> rolesToCompete = EnumSet.noneOf(Role.class);
            EnumSet<Role> rolesOfReject = EnumSet.noneOf(Role.class);
            boolean goon = true;
            while (true) {
                boolean willingToCompete = isWillingToCompete(currentRole);
                records.add(new CompeteRecord(currentRole, willingToCompete));
                if (!willingToCompete) {
                    rolesOfReject.add(currentRole);
                    rolesToCompete.remove(currentRole);
                } else {
                    if (rolesToCompete.contains(currentRole)) {
                        break;
                    } else {
                        rolesToCompete.add(currentRole);
                    }
                }
                if (rolesOfReject.size() == 2) {
                    if (rolesToCompete.size() == 1) {
                        currentRole = rolesToCompete.iterator().next();
                        break;
                    }
                } else if (rolesOfReject.size() == 3) {
                    goon = false;
                    break;
                }
                while (rolesOfReject.contains(currentRole = currentRole.getNext())) ;
            }
            // play
            if (goon) {
                invisibleCards.transfer(getOpenCards(), currentRole.getCards());
                invisibleCards.transfer(getMyCards(), Role.Me.getCards());
                while (true) {
                    CardSet offeredCards = getOfferedCards(currentRole);
                    records.add(new OfferRecord(currentRole, offeredCards));
                    if (!offeredCards.isEmpty()) {
                        invisibleCards.transfer(offeredCards, currentRole.getCards());
                        if (currentRole.getCards().isEmpty()) {
                            break;
                        }
                    }
                    currentRole = currentRole.getNext();
                }
            }
        }
    }

    protected abstract Role getStarter();

    protected abstract boolean isWillingToCompete(Role role);

    protected abstract CardSet getOpenCards();

    protected abstract CardSet getMyCards();

    protected abstract CardSet getOfferedCards(Role role);
}
