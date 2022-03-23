package edu.hitsz.propsfactory;

import edu.hitsz.props.AbstractProps;
import edu.hitsz.props.BloodProps;

import java.util.LinkedList;
import java.util.List;

public class BloodFactory implements PropsFactory {

    private List<AbstractProps> props;

    public BloodFactory () {

    }

    @Override
    public List<AbstractProps> createProps (int locationX, int locationY, int speedX, int speedY) {
        props = new LinkedList<>();
        BloodProps bloodProps = new BloodProps(locationX, locationY, speedX, speedY);
        props.add(bloodProps);
        return props;
    }

}
