package com.infinitops.musicaltickets.model;

public class Schedule {
    private Venue venue;
    private String date;
    private String time;
    private Musical musical;

    public Schedule(Venue venue, String date, String time, Musical musical) {
        this.venue = venue;
        this.date = date;
        this.time = time;
        this.musical = musical;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Musical getMusical() {
        return musical;
    }

    public void setMusical(Musical musical) {
        this.musical = musical;
    }
}
