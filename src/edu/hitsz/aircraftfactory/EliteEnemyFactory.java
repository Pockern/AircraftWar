package edu.hitsz.aircraftfactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteEnemy;

import java.util.LinkedList;
import java.util.List;

public class EliteEnemyFactory implements AircraftFactory {

    private List<AbstractAircraft> aircraft;

    public EliteEnemyFactory () {

    }

    @Override
    public List<AbstractAircraft> createAircraft (int locationX, int locationY, int speedX, int speedY, int hp) {
        aircraft = new LinkedList<>();
        EliteEnemy eliteEnemy = new EliteEnemy(locationX, locationY, speedX, speedY, hp);
        aircraft.add(eliteEnemy);
        return aircraft;
    }
}
