package edu.hitsz.props;

import edu.hitsz.aircraft.AbstractAircraft;

public class BloodProps extends AbstractProps {

    private final int treatment = 40;

    public BloodProps(int locationX, int locationY) {
        super(locationX, locationY);
    }

    @Override
    public void useProps(AbstractAircraft abstractAircraft) {
        abstractAircraft.increaseHp(treatment);
    }
}
