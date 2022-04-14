package edu.hitsz.shootstrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.BossBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class Scattering implements ShootStrategy {

    private int bulletDirection;
    private int shootNum;
    private int power;
    private int locationX;
    private int locationY;
    private int speedX;
    private int speedY;
    private BaseBullet baseBullet;
    private List<BaseBullet> bulletList = new LinkedList<>();

    @Override
    public List<BaseBullet> createBullet(AbstractAircraft aircraft) {

        if(aircraft instanceof BossEnemy) {
            locationX = aircraft.getLocationX() + aircraft.getShootDirection()*2;
            locationY = aircraft.getLocationY();
            speedX = 10;
            speedY = aircraft.getSpeedY() + aircraft.getShootDirection()*5;
            power = aircraft.getPower();

            for(int i = 0; i < aircraft.getShootNum(); i++ ) {
                if(i == 0) {
                    baseBullet = new BossBullet(locationX, locationY, -speedX, speedY, power);
                    bulletList.add(baseBullet);
                } else if (i == 1) {
                    baseBullet = new BossBullet(locationX, locationY, 0, speedY, power);
                    bulletList.add(baseBullet);
                } else {
                    baseBullet = new BossBullet(locationX, locationY, speedX, speedY, power);
                    bulletList.add(baseBullet);
                }
            }
        } else if (aircraft instanceof HeroAircraft) {
            locationX = aircraft.getLocationX() + aircraft.getShootDirection()*2;
            locationY = aircraft.getLocationY();
            speedX = 5;
            speedY = aircraft.getSpeedY() + aircraft.getShootDirection()*5;
            power = aircraft.getPower();
            aircraft.setShootNum(3);

            for(int i = 0; i < aircraft.getShootNum(); i++) {
                if(i == 0) {
                    baseBullet = new HeroBullet(locationX, locationY, -speedX, speedY, power);
                    bulletList.add(baseBullet);
                } else if (i == 1) {
                    baseBullet = new HeroBullet(locationX, locationY, 0, speedY, power);
                    bulletList.add(baseBullet);
                } else {
                    baseBullet = new HeroBullet(locationX, locationY, speedX, speedY, power);
                    bulletList.add(baseBullet);
                }
            }
        }

        return bulletList;
    }
}
