package edu.hitsz.aircraftfactory;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.shootstrategy.ShootStrategy;

public class MobEnemyFactory extends AircraftFactory{

    public MobEnemyFactory () {

    }

    @Override
    public EnemyAircraft createAircraft () {
        int locationX = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth()));
        int locationY = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2);
        int speedX = 0;
        int speedY = 10;
        int hp = 30;
        int shootNum = 0;
        int power = 0;
        int shootDirection = 1;
        ShootStrategy shootStrategy = null;


        EnemyAircraft aircraft = new MobEnemy(locationX, locationY, speedX, speedY, hp, shootNum, power, shootDirection, shootStrategy);
        return aircraft;
    }

}
