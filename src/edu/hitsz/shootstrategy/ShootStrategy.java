package edu.hitsz.shootstrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public interface ShootStrategy {
    /**
     * @param abstractAircraft 决定子弹列类型的飞机类型
     * @return List<BaseBullet> 返回一个子弹列
     */
    List<BaseBullet> createBullet(AbstractAircraft abstractAircraft);
}
