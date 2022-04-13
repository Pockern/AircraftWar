package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.BossBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.props.AbstractProps;

import java.util.LinkedList;
import java.util.List;

/**
 * boss敌机
 *
 * @author Pockern
 */
public class BossEnemy extends EnemyAircraft {

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1) （by teacher
     * 以代码为准，maybe向上是-1，向下为1
     */
    private final int bulletDirection = 1;

    /**
     * 子弹伤害
     */
    private final int power = 40;

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private final int shootNum = 3;

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    /**
     *boss机道具掉落
     */
    @Override
    public List<AbstractProps> dropProps() {
        List<AbstractProps> props = new LinkedList<>();
        return props;
    }

    /**
     *boss机射击
     */
    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        BossBullet bossBullet;
        int x = this.getLocationX();
        int y = this.getLocationY();
        int speedY = bulletDirection * 10;

        for(int i = 0; i < shootNum; i++ ) {
            if(i == 0) {
                bossBullet = new BossBullet(locationX, locationY, -15, speedY, power);
                res.add(bossBullet);
            } else if (i == 1) {
                bossBullet = new BossBullet(locationX, locationY, 0, speedY, power);
                res.add(bossBullet);
            } else {
                bossBullet = new BossBullet(locationX, locationY, 15, speedY, power);
                res.add(bossBullet);
            }
        }
        return res;
    }


    @Override
    public void forward() {
        locationX += speedX;
        locationY += speedY;
        if (locationX <= 0 + ImageManager.BOSS_ENEMY_IMAGE.getWidth()/2 || locationX >= Main.WINDOW_WIDTH - ImageManager.BOSS_ENEMY_IMAGE.getWidth()/2) {
            // 横向超出边界后反向，保证飞机不会飞出边界
            speedX = -speedX;
        }
    }

}
