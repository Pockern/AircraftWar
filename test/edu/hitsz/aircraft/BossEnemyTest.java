package edu.hitsz.aircraft;

import edu.hitsz.aircraftfactory.BossEnemyFactory;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.shootstrategy.Scattering;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BossEnemyTest {

    private EnemyAircraft bossEnemy;
    private List<BaseBullet> bossBulletList;

    @BeforeEach
    void setUp() {
        BossEnemyFactory bossEnemyFactory = new BossEnemyFactory();
        bossEnemy = bossEnemyFactory.createAircraft();
        bossBulletList = new LinkedList<>();
    }

    @AfterEach
    void tearDown() {
        bossEnemy = null;
    }

    @Test
    void shoot() {
        //子弹列非空
        bossBulletList.addAll(bossEnemy.shoot());
        assertNotNull(bossBulletList);
        //子弹列数量是否符合预期
        assertEquals(3, bossBulletList.size());
    }
}