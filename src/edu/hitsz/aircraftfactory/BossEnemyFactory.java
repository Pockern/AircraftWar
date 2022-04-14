package edu.hitsz.aircraftfactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.shootstrategy.Scattering;
import edu.hitsz.shootstrategy.ShootStrategy;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Pockern
 */
public class BossEnemyFactory extends AircraftFactory {

    public BossEnemyFactory () {

    }

    @Override
    public EnemyAircraft createAircraft () {
        int locationX = Main.WINDOW_WIDTH / 2;
        int locationY = ImageManager.HERO_IMAGE.getHeight();
        int speedX = 2;
        int speedY = 0;
        int hp = 120;
        int shootNum = 3;
        int power = 1;
        int shootDirection = 1;
        ShootStrategy shootStrategy = new Scattering();

        EnemyAircraft aircraft = new BossEnemy(locationX, locationY, speedX, speedY, hp, shootNum, power, shootDirection, shootStrategy);
        return aircraft;
    }
}
