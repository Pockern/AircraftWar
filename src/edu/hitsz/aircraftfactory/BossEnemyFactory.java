package edu.hitsz.aircraftfactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Pockern
 */
public class BossEnemyFactory extends AircraftFactory {

    private List<EnemyAircraft> aircraft;

    public BossEnemyFactory () {

    }

    @Override
    public List<EnemyAircraft> createAircraft () {
        int locationX = Main.WINDOW_WIDTH / 2;
        int locationY = ImageManager.HERO_IMAGE.getHeight();
        int speedX = 2;
        int speedY = 0;
        int hp = 180;

        aircraft = new LinkedList<>();
        BossEnemy bossEnemy = new BossEnemy(locationX, locationY, speedX, speedY, hp);
        aircraft.add(bossEnemy);
        return aircraft;
    }
}
