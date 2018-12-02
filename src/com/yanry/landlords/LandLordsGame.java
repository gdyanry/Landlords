package com.yanry.landlords;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: yanry
 * @Date: 2018/12/2 13:39
 */
public abstract class LandLordsGame {

    public void start(Role startFrom) {
        // init
        CardSet unknownCards = CardSet.getFullSet();
        Role.Left.setNext(Role.Me);
        Role.Me.setNext(Role.Right);
        Role.Right.setNext(Role.Left);
        List<Record> records = new LinkedList<>();
        Role currentRole = startFrom;
        // compete
        EnumSet<Role> rolesToCompete = EnumSet.noneOf(Role.class);
        int rejectTimes = 0;
        while (true) {
            boolean willingToCompete = isWillingToCompete(currentRole);
            records.add(new Record(currentRole, willingToCompete));
            if (!willingToCompete) {
                if (++rejectTimes == 2) {
                    if (rolesToCompete.size() == 1) {
                        currentRole = rolesToCompete.iterator().next();
                        break;
                    } else {
                        currentRole = currentRole.getNext();
                    }
                }
            } else {
                if (rolesToCompete.contains(currentRole)) {
                    break;
                } else {
                    rolesToCompete.add(currentRole);
                    currentRole = currentRole.getNext();
                }
            }
        }
        // play
        unknownCards.transfer(getOpenCards(), currentRole.getCards());
        unknownCards.transfer(getMyCards(), Role.Me.getCards());
    }

    protected abstract boolean isWillingToCompete(Role role);

    protected abstract CardSet getOpenCards();

    protected abstract CardSet getMyCards();
}
