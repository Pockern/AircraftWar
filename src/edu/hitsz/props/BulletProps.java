package edu.hitsz.props;

import edu.hitsz.aircraft.AbstractAircraft;

public class BulletProps extends AbstractProps {

    public BulletProps(int locationX, int locationY) {
        super(locationX, locationY);
    }

    @Override
    public void useProps(AbstractAircraft abstractAircraft) {
        System.out.println("FireSupply active!");
    }
}
