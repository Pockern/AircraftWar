package edu.hitsz.props;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;

import java.util.List;

public class BloodProps extends AbstractProps {

    private final int treatment = 40;

    public BloodProps(int locationX, int locationY) {
        super(locationX, locationY);
    }

    @Override
    public void useProps(List<EnemyAircraft> aircraftList) {
        AbstractAircraft aircraft = HeroAircraft.getHeroAircraft();
        aircraft.increaseHp(treatment);
    }


}
