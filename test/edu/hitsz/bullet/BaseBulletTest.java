package edu.hitsz.bullet;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BaseBulletTest {

    private BaseBullet baseBulletX;
    private BaseBullet baseBulletY;

    @BeforeEach
    void setUp() {
        //x轴出界
        baseBulletX = new BaseBullet(
                -1,
                100,
                0,
                10,
                20
        );
        //y轴出界
        baseBulletY = new BaseBullet(
                256,
                800,
                20,
                0,
                20
        );
    }

    @AfterEach
    void tearDown() {
        baseBulletX = null;
        baseBulletY = null;
    }

    @Test
    void forward() {
        baseBulletX.forward();
        //测试移动与是否出界
        assertAll(
                ()->assertEquals(110, baseBulletX.getLocationY()),
                ()->assertTrue(baseBulletX.notValid())
        );

        baseBulletY.forward();
        //同上
        assertAll(
                ()->assertEquals(276, baseBulletY.getLocationX()),
                ()->assertTrue(baseBulletY.notValid())
        );
    }

    /**
     * 由于crash()等方法大多数都是父类中的，且在此之前已经测试过了，具有一致性
     * 该类方法过少，故选取一个get方法进行测试（自己尚未拓展之前）
     */
    @Test
    void getPower() {
        assertEquals(20, baseBulletX.getPower());
    }
}