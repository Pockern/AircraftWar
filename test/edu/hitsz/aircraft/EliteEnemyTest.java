package edu.hitsz.aircraft;

import edu.hitsz.aircraftfactory.EliteEnemyFactory;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EliteEnemyTest {

    private EliteEnemy eliteEnemy;
    private MobEnemy mobEnemy1;
    private MobEnemy mobEnemy2;
    private List<BaseBullet> baseBulletList;

    EliteEnemyTest() {
    }

    @BeforeEach
    void setUp() {
        //实例化精英敌机
        eliteEnemy = new EliteEnemy(
                Main.WINDOW_WIDTH / 2,
                2 * ImageManager.ELITE_ENEMY_IMAGE.getHeight(),
                0,
                0,
                30
        );

        //检测一架碰撞飞机
        mobEnemy1 = new MobEnemy(
                Main.WINDOW_WIDTH / 2,
                2 * ImageManager.ELITE_ENEMY_IMAGE.getHeight(),
                0,
                0,
                30
        );

        //检测一架碰撞边缘但未碰撞的飞机
        mobEnemy2 = new MobEnemy(
                Main.WINDOW_WIDTH / 2,
                2 * ImageManager.MOB_ENEMY_IMAGE.getHeight(),
                0,
                0,
                30
        );

        baseBulletList = new LinkedList<>();
    }

    @AfterEach
    void tearDown() {
        eliteEnemy = null;
        mobEnemy1 = null;
        mobEnemy2 = null;
    }

    @Test
    void shoot() {
        //子弹列非空
        baseBulletList.addAll(eliteEnemy.shoot());
        assertNotNull(baseBulletList);
        //单次子弹仅一发
        assertEquals(1, baseBulletList.size());
    }

    @Test
    void crash() {
        assertAll(
                ()->assertTrue(eliteEnemy.crash(mobEnemy1)),
                ()->assertFalse(eliteEnemy.crash(mobEnemy2))
        );
    }
}