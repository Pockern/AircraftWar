package edu.hitsz.shootstrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class Direct implements ShootStrategy {

    private int locationX;
    private int locationY;
    private int speedX;
    private int speedY;
    private int shootDirection;
    private int shootNum;
    private int power;
    private BaseBullet baseBullet;
    private List<BaseBullet> bulletList = new LinkedList<>();

    @Override
    public List<BaseBullet> createBullet(AbstractAircraft aircraft) {

        if(aircraft instanceof HeroAircraft) {
            locationX = aircraft.getLocationX() + aircraft.getShootDirection()*2;
            locationY = aircraft.getLocationY();
            //speedX = 0, speedY = shootDirection * 4
            speedX = aircraft.getSpeedX();
            speedY = aircraft.getSpeedY() + aircraft.getShootDirection()*4;
            power = aircraft.getPower();
            shootNum = aircraft.getShootNum();

            for(int i = 0; i < shootNum; i++) {
                baseBullet = new HeroBullet(locationX, locationY, speedX, speedY, power);
                bulletList.add(baseBullet);
            }
        } else if (aircraft instanceof EliteEnemy) {
            locationX = aircraft.getLocationX() + aircraft.getShootDirection()*2;
            locationY = aircraft.getLocationY();
            speedX = 0;
            speedY = aircraft.getSpeedY() + aircraft.getShootDirection()*4;
            shootNum = aircraft.getShootNum();
            power = aircraft.getPower();

            for(int i = 0; i < shootNum; i++) {
                baseBullet = new EnemyBullet(locationX, locationY, speedX, speedY, power);
                bulletList.add(baseBullet);
            }
        }

        return bulletList;
    }

}
