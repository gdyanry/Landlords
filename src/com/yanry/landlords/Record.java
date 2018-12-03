package com.yanry.landlords;

/**
 * @Author: yanry
 * @Date: 2018/12/2 23:18
 */
public class Record {
    private Role who;

    public Record(Role who) {
        this.who = who;
    }

    public Role getWho() {
        return who;
    }
}
