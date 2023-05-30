package org.example.employees;

public interface Flyer {
    int getHoursFlown();

    void setHoursFlown(int hoursFlown);

    boolean isInstrumentFlightRating();

    void setInstrumentFlightRating(boolean instrumentFlightRating);

    void fly();
}
