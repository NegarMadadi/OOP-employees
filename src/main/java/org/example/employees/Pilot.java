package org.example.employees;

public class Pilot implements Flyer {
    private int hoursFlown = 0;
    private boolean instrumentFlightRating = false;

    public Pilot(int hoursFlown, boolean instrumentFlightRading) {
        this.hoursFlown = hoursFlown;
        this.instrumentFlightRating = instrumentFlightRading;
    }

    @Override
    public int getHoursFlown() {
        return hoursFlown;
    }

    @Override
    public void setHoursFlown(int hoursFlown) {
        this.hoursFlown = hoursFlown;
    }

    @Override
    public boolean isInstrumentFlightRating() {
        return instrumentFlightRating;
    }

    @Override
    public void setInstrumentFlightRating(boolean instrumentFlightRating) {
        this.instrumentFlightRating = instrumentFlightRating;
    }

    @Override
    public void fly(){
        System.out.println("Prepare for take off!");
    }
}
