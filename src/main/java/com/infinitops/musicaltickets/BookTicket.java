package com.infinitops.musicaltickets;

import com.infinitops.musicaltickets.model.Schedule;
import com.infinitops.musicaltickets.model.Venue;

public class BookTicket {

    public Venue[] venues;
    private Schedule[] schedules;

    public Venue[] getVenues() {
        return venues;
    }

    public void setVenues(Venue[] venues) {
        this.venues = venues;
    }

    public Schedule[] getSchedules() {
        return schedules;
    }

    public void setSchedules(Schedule[] schedules) {
        this.schedules = schedules;
    }
}
