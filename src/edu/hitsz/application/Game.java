package edu.hitsz.application;

import edu.hitsz.aircraft.*;
import edu.hitsz.aircraftfactory.AircraftFactory;
import edu.hitsz.aircraftfactory.BossEnemyFactory;
import edu.hitsz.aircraftfactory.EliteEnemyFactory;
import edu.hitsz.aircraftfactory.MobEnemyFactory;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.dao.ScoreDao;
import edu.hitsz.dao.ScoreDaoImpl;
import edu.hitsz.dao.ScoreRecord;
import edu.hitsz.props.AbstractProps;
import edu.hitsz.props.BloodProps;
import edu.hitsz.props.BombProps;
import edu.hitsz.props.BulletProps;
import edu.hitsz.propsfactory.BloodFactory;
import edu.hitsz.propsfactory.BombFactory;
import edu.hitsz.propsfactory.BulletFactory;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public class Game extends JPanel {

    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;

    private HeroAircraft heroAircraft;
    private List<EnemyAircraft> enemyAircrafts;
    private List<BaseBullet> heroBullets;
    private List<BaseBullet> enemyBullets;
    private List<AbstractProps> enemyDrop;
    private AircraftFactory aircraftFactory;

    private int enemyMaxNumber = 5;

    private boolean gameOverFlag = false;
    private boolean isBoss = false;
    private int score = 0;
    private int scoreLimit = 1;
    private int time = 0;
    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 600;
    private int cycleTime = 0;


    public Game() {
        heroAircraft = HeroAircraft.getHeroAircraft();
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        enemyDrop = new LinkedList<>();

        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {

        // 产生随机数以随机生成普通敌机或精英敌机
        Random r = new Random();

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {

                //判断当前敌机中是否仍有Boss机存活
                for(AbstractAircraft abstractAircraft : enemyAircrafts) {
                    if(abstractAircraft instanceof BossEnemy) {
                        isBoss = true;
                    } else {
                        isBoss = false;
                    }
                }

                //得分超过阈值，则生成Boss机(Boss机尚未存在)
                //分数为300倍数时，若无Boss机，生成Boss
                if (score >= 300  * scoreLimit && !isBoss) {
                    isBoss = true;
                    aircraftFactory = new BossEnemyFactory();
                    enemyAircrafts.add( aircraftFactory.createAircraft() );
                    scoreLimit += 1;
                }

                //随机数产生，以产生战机概率和物品掉落概率
                int rdEnemy = r.nextInt(4);

                System.out.println(time);
                // 新敌机产生
                // 普通敌机 : 精英敌机 = 3 : 1
                if (enemyAircrafts.size() < enemyMaxNumber && rdEnemy <= 2) {
                    aircraftFactory = new MobEnemyFactory();
                    enemyAircrafts.add( aircraftFactory.createAircraft() );
                }
                else if (enemyAircrafts.size() < enemyMaxNumber) {
                    aircraftFactory = new EliteEnemyFactory();
                    enemyAircrafts.add( aircraftFactory.createAircraft() );
                }

                // 飞机射出子弹
                shootAction();
            }

            // 子弹移动
            bulletsMoveAction();

            //道具移动
            propsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;

                //排行榜相关
                rankingListAction();

//                System.out.println("****************************************************");
//                System.out.println("                    得分排行榜                        ");
//                System.out.println("****************************************************");
//
//                ScoreDao scoreDao = new ScoreDaoImpl();
//                //按想要的格式获取当前时间输出
//                Date date = new Date();
//                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                String dateNow = ft.format(date);
//                //添加本次得分记录
//                ScoreRecord scoreRecord = new ScoreRecord("testUserName", score, dateNow);
//                scoreDao.doAdd(scoreRecord);
//
//                List<ScoreRecord> scoreRecordList = scoreDao.getAllScoreRecord();
//                for(int i = 0; i < scoreRecordList.size(); i++) {
//                    int j = i+1;
//                    System.out.println("第"+ j + "名： " + scoreRecordList.get(i));
//                }

                System.out.println("Game Over!");
            }

        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        // 敌机射击
        for (AbstractAircraft enemy : enemyAircrafts) {
            enemyBullets.addAll(enemy.shoot());
        }
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
    }

    //道具移动
    private void propsMoveAction() {
        for (AbstractProps props : enemyDrop) {
            props.forward();
        }
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    private void rankingListAction() {
        System.out.println("****************************************************");
        System.out.println("                    得分排行榜                        ");
        System.out.println("****************************************************");

        ScoreDao scoreDao = new ScoreDaoImpl();
        //按想要的格式获取当前时间输出
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateNow = ft.format(date);
        //添加本次得分记录
        ScoreRecord scoreRecord = new ScoreRecord("testUserName", score, dateNow);
        scoreDao.doAdd(scoreRecord);

        List<ScoreRecord> scoreRecordList = scoreDao.getAllScoreRecord();
        for(int i = 0; i < scoreRecordList.size(); i++) {
            int j = i+1;
            System.out.println("第"+ j + "名： " + scoreRecordList.get(i));
        }
    }

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        for (BaseBullet enemyBullet : enemyBullets) {
            //子弹消失，则看下一战机的子弹是否命中
            if (enemyBullet.notValid()) {
                continue;
            }

            //如果被敌机子弹命中，则降低英雄机血量，敌机子弹消失
            if (heroAircraft.crash(enemyBullet)) {
                heroAircraft.decreaseHp(enemyBullet.getPower());
                enemyBullet.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (EnemyAircraft enemyAircraft : enemyAircrafts) {

                // 已被其他子弹击毁的敌机，不再检测
                // 避免多个子弹重复击毁同一敌机的判定
                if (enemyAircraft.notValid()) {
                    continue;
                }

                // 敌机撞击到英雄机子弹
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失子弹伤害的生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    //子弹消失
                    bullet.vanish();

                    //英雄机获得分数，并在精英敌机死亡处产生道具
                    if (enemyAircraft.notValid()) {
                        //获得分数，产生道具补给
                        score += 20;
                        enemyDrop.addAll( enemyAircraft.dropProps() );
                    }
                }

                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // 我方获得道具，道具生效
        for (AbstractProps propsDrop : enemyDrop) {

            //道具消失或生效，则遍历下一个道具
            if (propsDrop.notValid()) {
                continue;
            }
            //道具和英雄机相撞则发挥效用
            if (heroAircraft.crash(propsDrop) || propsDrop.crash(heroAircraft)) {
                propsDrop.useProps();
                propsDrop.vanish();
            }

        }
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 检查英雄机生存
     * 4. 删除无效的道具
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        enemyDrop.removeIf(AbstractProps::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        //TODO boss机子弹绘制怎么说
        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        //绘制道具
        paintImageWithPositionRevised(g, enemyDrop);

        paintImageWithPositionRevised(g, enemyAircrafts);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }


}
