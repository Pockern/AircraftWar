package edu.hitsz.aircraftfactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.MobEnemy;

import java.util.LinkedList;
import java.util.List;

public class MobEnemyFactory implements AircraftFactory{

    private List<AbstractAircraft> aircraft;

    public MobEnemyFactory () {

    }

    @Override
    public List<AbstractAircraft> createAircraft (int locationX, int locationY, int speedX, int speedY, int hp) {
        aircraft = new LinkedList<>();
        MobEnemy mobEnemy = new MobEnemy(locationX, locationY, speedX, speedY, hp);
        aircraft.add(mobEnemy);
        return aircraft;
    }

}
