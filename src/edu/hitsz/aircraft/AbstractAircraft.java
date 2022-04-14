package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.props.AbstractProps;
import edu.hitsz.shootstrategy.ShootStrategy;

import java.util.LinkedList;
import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;

    /**
     * 子弹一次发射数量
     */
    protected int shootNum;

    /**
     * 子弹伤害
     */
    protected int power;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    protected int shootDirection;

    /**
     * 射击策略
     */
    protected ShootStrategy shootStrategy;

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp, int shootNum, int power, int shootDirection, ShootStrategy shootStrategy) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
        this.shootNum = shootNum;
        this.power = power;
        this.shootDirection = shootDirection;
        this.shootStrategy = shootStrategy;
    }

    public void setShootStrategy(ShootStrategy shootStrategy) {
        this.shootStrategy = shootStrategy;
    }

    public ShootStrategy getShootStrategy() { return this.shootStrategy; }

    public List<BaseBullet> shoot() {
        if (this.getShootStrategy() == null) {
            return new LinkedList<>();
        } else {
            return shootStrategy.createBullet(this);
        }
    }

    public void decreaseHp(int decrease) {
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }

    public void increaseHp(int increase) {
        hp += increase;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    public void setHp(int hp) {
        this.hp = hp;
        this.maxHp = hp;
    }

    public void setShootNum(int shootNum) {
        this.shootNum = shootNum;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setShootDirection(int shootDirection) { this.shootDirection = shootDirection; }

    public int getHp() {
        return hp;
    }

    public int getShootNum() { return shootNum; }

    public int getShootDirection() { return shootDirection; }

    public int getPower() { return power; }

}


