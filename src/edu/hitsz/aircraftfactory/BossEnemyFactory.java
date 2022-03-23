package edu.hitsz.aircraftfactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;

import java.util.LinkedList;
import java.util.List;

public class BossEnemyFactory implements AircraftFactory {

    private List<AbstractAircraft> aircraft;

    public BossEnemyFactory () {

    }

    public List<AbstractAircraft> createAircraft (int locationX, int locationY, int speedX, int speedY, int hp) {
        aircraft = new LinkedList<>();
        BossEnemy bossEnemy = new BossEnemy(locationX, locationY, speedX, speedY, hp);
        aircraft.add(bossEnemy);
        return aircraft;
    }
}
