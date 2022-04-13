package edu.hitsz.props;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

/**
 * @author Pockern
 */
abstract public class AbstractProps extends AbstractFlyingObject {

    /**
     * 道具的速度
     */
   protected int propsSpeedX = 0;
   protected int propsSpeedY = 10;

    public AbstractProps(int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.speedX = propsSpeedX;
        this.speedY = propsSpeedY;
    }

    public abstract void useProps(AbstractAircraft abstractAircraft);

    @Override
    public void forward() {
        super.forward();

        //y出界(向下)
        if (speedY > 0 && locationY >= Main.WINDOW_HEIGHT) {
            vanish();
        }else if (locationY <= 0) {
            //向上出界
            vanish();
        }
    }

}
