package com.infinitops.musicaltickets.model;

public class Venue {

    private int _id;
    private String name;
    private String[] seats;

    public Venue(int _id, String name, String[] seats) {
        this._id = _id;
        this.name = name;
        this.seats = seats;
    }

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSeats() {
        return seats;
    }

    public void setSeats(String[] seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
