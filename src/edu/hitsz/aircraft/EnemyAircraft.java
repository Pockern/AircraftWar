package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.props.AbstractProps;

import java.util.List;

public abstract class EnemyAircraft extends AbstractAircraft {

    public EnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    public abstract List<AbstractProps> dropProps();

}
