package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.props.AbstractProps;
import edu.hitsz.props.BloodProps;
import edu.hitsz.props.BombProps;
import edu.hitsz.props.BulletProps;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 精英敌机
 *
 * @author Pockern
 */
public class EliteEnemy extends EnemyAircraft {
    //精英机子弹方向
    private int bullet_direction = 1;
    //一次射出的子弹量
    private int shootNum = 1;
    //单发子弹伤害
    private int power = 20;

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        super.forward();
        //飞行至底端（能到底端自然存活）则消失
        if(locationY >= Main.WINDOW_HEIGHT) {
            vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> res_bullet = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + bullet_direction *2;
        int speedX = 0;
        int speedY = this.getSpeedY() + bullet_direction *4;
        BaseBullet baseBullet;
        for(int i = 0; i < shootNum; i++) {
            baseBullet = new EnemyBullet(x, y, speedX, speedY, power);
            res_bullet.add(baseBullet);
        }
        return res_bullet;
    }

}
