package edu.hitsz.props;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.MobEnemy;

import java.util.List;

public class BombProps extends AbstractProps{

    public BombProps(int locationX, int locationY) {
        super(locationX, locationY);
    }

    @Override
    public void useProps() {
        //TODO 飞机是消了，但是分数没有加上啊
//        for(EnemyAircraft enemyAircraft : aircraftList) {
//            if(enemyAircraft instanceof MobEnemy || enemyAircraft instanceof EliteEnemy) {
//                enemyAircraft.vanish();
//            }
//        }
        System.out.println("BombSupply active!");
    }

}
