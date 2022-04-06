package edu.hitsz.aircraft;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import java.util.LinkedList;
import java.util.List;

/**
 * 精英敌机
 *
 * @author Pockern
 */
public class EliteEnemy extends EnemyAircraft {

    /**
     * 子弹方向，1为向下
     */
    private int bulletDirection = 1;

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 1;

    /**
     * 子弹伤害
     */
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
        List<BaseBullet> resBullet = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + bulletDirection *2;
        int speedX = 0;
        int speedY = this.getSpeedY() + bulletDirection *4;
        BaseBullet baseBullet;
        for(int i = 0; i < shootNum; i++) {
            baseBullet = new EnemyBullet(x, y, speedX, speedY, power);
            resBullet.add(baseBullet);
        }
        return resBullet;
    }

}
