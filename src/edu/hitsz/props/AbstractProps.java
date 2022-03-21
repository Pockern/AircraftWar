package edu.hitsz.props;

import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

abstract public class AbstractProps extends AbstractFlyingObject {

    public AbstractProps(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

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
