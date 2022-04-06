package edu.hitsz.bullet;

import edu.hitsz.application.Main;

/**
 * @author Pockern
 */
public class BossBullet extends BaseBullet {

    public BossBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }

    @Override
    public void forward() {
        locationX += speedX;
        locationY += speedY;

        //x轴越界则反弹
        if (locationX <= 0 || locationX >= Main.WINDOW_WIDTH) {
            speedX = -speedX;
        }
        //y轴越界则消失
        if (locationY >= Main.WINDOW_HEIGHT ) {
            // 向下飞行出界
            vanish();
        }else if (locationY <= 0){
            // 向上飞行出界
            vanish();
        }
    }

}
