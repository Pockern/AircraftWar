package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.shootstrategy.Direct;
import edu.hitsz.shootstrategy.ShootStrategy;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

    private volatile static HeroAircraft heroAircraft;

    /**
     * @param locationX      英雄机位置x坐标
     * @param locationY      英雄机位置y坐标
     * @param speedX         英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY         英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp             初始生命值
     * @param shootNum       单次发射的子弹数量
     * @param power          单发子弹的伤害
     * @param shootDirection 子弹射击方向 (向上发射：1，向下发射：-1)
     * @param shootStrategy  英雄机射击策略
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp, int shootNum, int power, int shootDirection, ShootStrategy shootStrategy) {
        super(locationX, locationY, speedX, speedY, hp, shootNum, power, shootDirection, shootStrategy);
    }

    /**
     *建立英雄机实例并返回
     * 单例模式
     */
    public static HeroAircraft getHeroAircraft() {

        if (heroAircraft == null) {
            synchronized (HeroAircraft.class) {
                if (heroAircraft == null) {
                    heroAircraft = new HeroAircraft(
                            Main.WINDOW_WIDTH / 2,
                            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight(),
                            0,
                            0,
                            100,
                            1,
                            30,
                            -1,
                            new Direct()
                    );
                }
            }
        }
        return heroAircraft;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

//    @Override
//    /**
//     * 通过射击产生子弹
//     * @return 射击出的子弹List
//     */
//    public List<BaseBullet> shoot() {
//        List<BaseBullet> res = new LinkedList<>();
//        int x = this.getLocationX();
//        int y = this.getLocationY() + bullet_direction *2;
//        int speedX = 0;
//        int speedY = this.getSpeedY() + bullet_direction *4;
//        BaseBullet baseBullet;
//        for(int i=0; i<shootNum; i++){
//            // 子弹发射位置相对飞机位置向前偏移
//            // 多个子弹横向分散
//            baseBullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
//            res.add(baseBullet);
//        }
//        return res;
//    }

}
