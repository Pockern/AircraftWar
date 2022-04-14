package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.props.AbstractProps;
import edu.hitsz.shootstrategy.ShootStrategy;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends EnemyAircraft {

    private List<AbstractProps> props;

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int shootNum, int power, int shootDirection, ShootStrategy shootStrategy) {
        super(locationX, locationY, speedX, speedY, hp, shootNum, power, shootDirection, shootStrategy);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    @Override
    public List<AbstractProps> dropProps() { return new LinkedList<>(); }

}
