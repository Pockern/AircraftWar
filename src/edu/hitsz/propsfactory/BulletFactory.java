package edu.hitsz.propsfactory;

import edu.hitsz.props.AbstractProps;
import edu.hitsz.props.BulletProps;

import java.util.LinkedList;
import java.util.List;

public class BulletFactory {

    private List<AbstractProps> props;

    public BulletFactory () {

    }

    public List<AbstractProps> createProps (int locationX, int locationY, int speedX, int speedY) {
        props = new LinkedList<>();
        BulletProps bullet = new BulletProps(locationX, locationY, speedX, speedY);
        props.add(bullet);
        return props;
    }

}
