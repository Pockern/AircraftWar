package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BossEnemyTest {

    private BossEnemy bossEnemy;
    private List<BaseBullet> bossBulletList;

    @BeforeEach
    void setUp() {
        bossEnemy = BossEnemy.getBossEnemy();
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

    @Test
    void getBossEnemy() {
        assertNotNull(bossEnemy);
    }
}