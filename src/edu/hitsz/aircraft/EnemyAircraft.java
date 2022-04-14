package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.props.AbstractProps;
import edu.hitsz.shootstrategy.ShootStrategy;

import java.util.List;

public abstract class EnemyAircraft extends AbstractAircraft {

    public EnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp, int shootNum, int power, int shootDirection, ShootStrategy shootStrategy) {
        super(locationX, locationY, speedX, speedY, hp, shootNum, power, shootDirection, shootStrategy);
    }

    /**
     * Enemy死亡后掉落道具
     */
    public abstract List<AbstractProps> dropProps();

}
