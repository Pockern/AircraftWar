package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.BossBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.props.AbstractProps;
import edu.hitsz.shootstrategy.ShootStrategy;

import java.util.LinkedList;
import java.util.List;

/**
 * boss敌机
 *
 * @author Pockern
 */
public class BossEnemy extends EnemyAircraft {

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int shootNum, int power, int shootDirection, ShootStrategy shootStrategy) {
        super(locationX, locationY, speedX, speedY, hp, shootNum, power, shootDirection, shootStrategy);
    }

    /**
     *boss机道具掉落
     */
    @Override
    public List<AbstractProps> dropProps() {
        List<AbstractProps> props = new LinkedList<>();
        return props;
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
