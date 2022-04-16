package edu.hitsz.aircraftfactory;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.shootstrategy.Straight;
import edu.hitsz.shootstrategy.ShootStrategy;

import java.util.Random;

public class EliteEnemyFactory extends AircraftFactory {

    public EliteEnemyFactory () {

    }

    @Override
    public EnemyAircraft createAircraft () {
        int locationX = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth()));
        int locationY = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2);
        int speedX = 5;
        int speedY = 10;
        int hp = 30;
        int shootNum = 1;
        int power = 20;
        int shootDirection = 1;
        ShootStrategy shootStrategy = new Straight();
        EnemyAircraft aircraft;

        Random random = new Random();
        int rd = random.nextInt(2);

        if ( rd == 0 ) {
            aircraft = new EliteEnemy(locationX, locationY, speedX, speedY, hp, shootNum, power, shootDirection, shootStrategy);
        } else {
            aircraft = new EliteEnemy(locationX, locationY, speedX, speedY, hp, shootNum, power, shootDirection, shootStrategy);
        }
        return aircraft;
    }
}
