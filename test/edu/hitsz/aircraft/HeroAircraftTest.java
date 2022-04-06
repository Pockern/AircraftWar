package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.props.AbstractProps;
import edu.hitsz.props.BloodProps;
import edu.hitsz.propsfactory.BloodFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {

    private HeroAircraft heroAircraft;
    private BloodProps bloodProps;
    private EnemyBullet enemyBullet;

    @BeforeEach
    void setUp() {
        //get英雄机实例
        heroAircraft = HeroAircraft.getHeroAircraft();

        //在英雄机位置制造加血道具
        bloodProps = new BloodProps(
                heroAircraft.getLocationX(),
                heroAircraft.getLocationY(),
                0,
                0
        );

        //创建敌机子弹实例
        enemyBullet = new EnemyBullet(
                heroAircraft.getLocationX(),
                heroAircraft.getLocationY(),
                0,
                0,
                20
        );
    }

    @AfterEach
    void tearDown() {
        heroAircraft = null;
        bloodProps = null;
        enemyBullet = null;
    }

    @Test
    void decreaseHp() {
        //子弹实例是否能成功减血
        heroAircraft.decreaseHp( enemyBullet.getPower() );
        assertEquals(80, heroAircraft.getHp());
    }

    @Test
    void increaseHp() {
        //100血降20再加40，血量应该不超过100，考虑了边界条件
        heroAircraft.decreaseHp( enemyBullet.getPower() );
        heroAircraft.increaseHp( bloodProps.getTreatment() );
        assertEquals(100, heroAircraft.getHp() );
    }

    @Test
    void getHeroAircraft() {
        assertNotNull(heroAircraft);
    }
}