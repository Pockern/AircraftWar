package edu.hitsz.aircraftfactory;

import edu.hitsz.aircraft.AbstractAircraft;

import java.util.List;

public interface AircraftFactory {
    public List<AbstractAircraft> createAircraft (int locationX, int locationY, int speedX, int speedY, int hp);
}
