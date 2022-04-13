package edu.hitsz.props;

import edu.hitsz.aircraft.AbstractAircraft;

public class BombProps extends AbstractProps{

    public BombProps(int locationX, int locationY) {
        super(locationX, locationY);
    }

    @Override
    public void useProps(AbstractAircraft abstractAircraft) {
        System.out.println("BombSupply active!");
    }

}
