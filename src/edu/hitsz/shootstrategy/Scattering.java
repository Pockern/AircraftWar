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

    private BaseBullet baseBullet;
    private List<BaseBullet> bulletList = new LinkedList<>();

    @Override
    public List<BaseBullet> createBullet(AbstractAircraft aircraft) {

        if(aircraft instanceof BossEnemy) {
            int locationX = aircraft.getLocationX() + aircraft.getShootDirection()*2;
            int locationY = aircraft.getLocationY();
            int speedX = 10;
            int speedY = aircraft.getSpeedY() + aircraft.getShootDirection()*5;
            int power = aircraft.getPower();

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
            int locationX = aircraft.getLocationX() + aircraft.getShootDirection()*2;
            int locationY = aircraft.getLocationY();
            int speedX = 5;
            int speedY = aircraft.getSpeedY() + aircraft.getShootDirection()*5;
            int power = aircraft.getPower();
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
