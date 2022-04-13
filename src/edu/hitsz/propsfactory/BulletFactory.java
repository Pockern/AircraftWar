package edu.hitsz.propsfactory;

import edu.hitsz.props.AbstractProps;
import edu.hitsz.props.BulletProps;

import java.util.LinkedList;
import java.util.List;

public class BulletFactory extends PropsFactory {

    private List<AbstractProps> props;

    public BulletFactory () {

    }

    @Override
    public List<AbstractProps> createProps (int locationX, int locationY) {
        props = new LinkedList<>();
        BulletProps bullet = new BulletProps(locationX, locationY);
        props.add(bullet);
        return props;
    }

}
