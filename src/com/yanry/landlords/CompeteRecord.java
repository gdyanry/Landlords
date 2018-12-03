package com.yanry.landlords;

public class CompeteRecord extends Record {
    private boolean isWilling;

    public CompeteRecord(Role by, boolean isWilling) {
        super(by);
        this.isWilling = isWilling;
    }

    public boolean isWilling() {
        return isWilling;
    }
}
