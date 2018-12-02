package com.yanry.landlords;

/**
 * @Author: yanry
 * @Date: 2018/12/2 23:18
 */
public class Record {
    private Role by;
    private Object action;

    public Record(Role by, Object action) {
        this.by = by;
        this.action = action;
    }
}
