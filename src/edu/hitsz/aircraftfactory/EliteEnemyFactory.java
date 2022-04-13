package edu.hitsz.aircraftfactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import java.util.LinkedList;
import java.util.List;

public class EliteEnemyFactory extends AircraftFactory {

    private List<EnemyAircraft> aircraft;

    public EliteEnemyFactory () {

    }

    @Override
    public List<EnemyAircraft> createAircraft () {
        int locationX = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth()));
        int locationY = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2);
        int speedX = 0;
        int speedY = 10;
        int hp = 30;

        aircraft = new LinkedList<>();
        EliteEnemy eliteEnemy = new EliteEnemy(locationX, locationY, speedX, speedY, hp);
        aircraft.add(eliteEnemy);
        return aircraft;
    }
}
