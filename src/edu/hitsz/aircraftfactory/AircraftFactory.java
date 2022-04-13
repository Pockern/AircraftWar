package edu.hitsz.aircraftfactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EnemyAircraft;

import java.util.List;

/**
 * @author Pockern
 */
public abstract class AircraftFactory {
    public abstract List<EnemyAircraft> createAircraft ();
}
