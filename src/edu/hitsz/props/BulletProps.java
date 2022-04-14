package edu.hitsz.props;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.shootstrategy.Scattering;

import java.util.List;

public class BulletProps extends AbstractProps {

    public BulletProps(int locationX, int locationY) {
        super(locationX, locationY);
    }

    @Override
    public void useProps(List<EnemyAircraft> aircraftList) {
        AbstractAircraft aircraft = HeroAircraft.getHeroAircraft();
        aircraft.setShootStrategy( new Scattering() );
    }
}
