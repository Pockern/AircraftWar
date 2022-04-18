package edu.hitsz.shootstrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class Straight implements ShootStrategy {

    private BaseBullet baseBullet;
    private List<BaseBullet> bulletList = new LinkedList<>();

    @Override
    public List<BaseBullet> createBullet(AbstractAircraft aircraft) {

        if(aircraft instanceof HeroAircraft) {
            int locationX = aircraft.getLocationX() + aircraft.getShootDirection()*2;
            int locationY = aircraft.getLocationY();
            //speedX = 0, speedY = shootDirection * 4
            int speedX = aircraft.getSpeedX();
            int speedY = aircraft.getSpeedY() + aircraft.getShootDirection()*4;
            int power = aircraft.getPower();
            int shootNum = aircraft.getShootNum();

            for(int i = 0; i < shootNum; i++) {
                baseBullet = new HeroBullet(locationX, locationY, speedX, speedY, power);
                bulletList.add(baseBullet);
            }
        } else if (aircraft instanceof EliteEnemy) {
            int locationX = aircraft.getLocationX() + aircraft.getShootDirection()*2;
            int locationY = aircraft.getLocationY();
            int speedX = 0;
            int speedY = aircraft.getSpeedY() + aircraft.getShootDirection()*4;
            int shootNum = aircraft.getShootNum();
            int power = aircraft.getPower();

            for(int i = 0; i < shootNum; i++) {
                baseBullet = new EnemyBullet(locationX, locationY, speedX, speedY, power);
                bulletList.add(baseBullet);
            }
        }

        return bulletList;
    }

}
