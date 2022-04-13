package edu.hitsz.aircraft;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.props.AbstractProps;
import edu.hitsz.props.BloodProps;
import edu.hitsz.propsfactory.BloodFactory;
import edu.hitsz.propsfactory.BombFactory;
import edu.hitsz.propsfactory.BulletFactory;
import edu.hitsz.propsfactory.PropsFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

    /**
     * 道具移动速度
     */
    private int propsSpeedX = 0;
    private int propsSpeedY = this.getSpeedY();

    /**
     * 道具工厂基类型，以便生成道具
     */
    private PropsFactory propsFactory;

    /**
     * 道具列表，用于存储实例化道具
     */
    private List<AbstractProps> propsList = new LinkedList<>();

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
    public List<AbstractProps> dropProps() {
        //随机数提供道具掉落概率
        Random random = new Random();
        int rdProps = random.nextInt(4);

        if (rdProps == 0) {
             propsFactory = new BloodFactory();
             propsList.addAll(propsFactory.createProps(
                     this.getLocationX(),
                     this.getLocationY()
             ));
        } else if (rdProps == 1) {
            propsFactory = new BombFactory();
            propsList.addAll(propsFactory.createProps(
                    this.getLocationX(),
                    this.getLocationY()
            ));
        } else if (rdProps == 2) {
            propsFactory = new BulletFactory();
            propsList.addAll(propsFactory.createProps(
                    this.getLocationX(),
                    this.getLocationY()
            ));
        } else {
            //不产生道具
        }

        return propsList;
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
