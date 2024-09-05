package com.qsp.model;

public class Bank {
    private int bid;
    private String name;
    private String loc;

    @Override
    public String toString() {
        return "[bid: " + bid + ", name: " + name + ", loc: " + loc + ']';
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
