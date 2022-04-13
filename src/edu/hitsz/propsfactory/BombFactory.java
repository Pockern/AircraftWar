package edu.hitsz.propsfactory;

import edu.hitsz.props.AbstractProps;
import edu.hitsz.props.BombProps;

import java.util.LinkedList;
import java.util.List;

public class BombFactory extends PropsFactory {

    private List<AbstractProps> props;

    public BombFactory () {

    }

    @Override
    public List<AbstractProps> createProps (int locationX, int locationY) {
        props = new LinkedList<>();
        BombProps bomb = new BombProps(locationX, locationY);
        props.add(bomb);
        return props;
    }

}
