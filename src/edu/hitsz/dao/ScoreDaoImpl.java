package edu.hitsz.dao;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pockern
 */
public class ScoreDaoImpl implements ScoreDao{

    private File file = new File("database\\ScoreList.txt");

    private List<ScoreRecord> scoreRecords;

    public ScoreDaoImpl() {
        scoreRecords = new LinkedList<>();

        //文件非空则读取
        try {
            if(file.length() > 0) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("database\\ScoreList.txt"));
                scoreRecords = (List<ScoreRecord>) ois.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ScoreRecord findByPlayerName(String playerName) {
        for(ScoreRecord scoreRecord : scoreRecords) {
            //这里IDE提示改动
            if (scoreRecord.getPlayerName().equals(playerName)) {
                return scoreRecord;
            }
        }

        return null;
    }

    @Override
    public List<ScoreRecord> getAllScoreRecord() {
        return scoreRecords;
    }

    @Override
    public void doAdd(ScoreRecord scoreRecord) {

        boolean insert = false;
        int i = 0;

        //判断插入位置
        //scoreRecord.size()是会动态变化的。。。
        for(i = 0; i < scoreRecords.size(); i++) {
            //大数据插入上面
            if(scoreRecords.get(i).getScore() < scoreRecord.getScore()) {
                insert = true;
                break;
            }
        }

        if(insert) {
            //若有比该对象得分小的对象存在，则插入到其位置
            scoreRecords.add(i, scoreRecord);
        } else if(!insert) {
            // 如果得分为最小，则插入到最后
            scoreRecords.add(scoreRecord);
        }

        //序列化输入到文件中
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("database\\ScoreList.txt"));
            oos.writeObject(scoreRecords);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO
    @Override
    public void doDelete() {

    }

    //just for test
//    public static void main(String[] args) {
//        Date date = new Date();
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//        String dateNow;
//        dateNow = ft.format(date);
//
//        ScoreRecord scoreRecord0 = new ScoreRecord("zhangsan", 10, dateNow);
//        ScoreRecord scoreRecord1 = new ScoreRecord("zhangsan", 30, dateNow);
//        ScoreRecord scoreRecord2 = new ScoreRecord("zhangsan", 20, dateNow);
//        ScoreRecord scoreRecord3 = new ScoreRecord("zhangsan", 80, dateNow);
//        ScoreRecord scoreRecord4 = new ScoreRecord("zhangsan", 5, dateNow);
//        System.out.println(scoreRecord0);
//        System.out.println(scoreRecord1);
//        System.out.println(scoreRecord2);
//        System.out.println(scoreRecord3);
//        System.out.println(scoreRecord4);
//
//        ScoreDao scoreDao = new ScoreDaoImpl();
//        scoreDao.doAdd(scoreRecord0);
//        scoreDao.doAdd(scoreRecord1);
//        scoreDao.doAdd(scoreRecord2);
//        scoreDao.doAdd(scoreRecord3);
//        scoreDao.doAdd(scoreRecord4);
//
//        List<ScoreRecord> recordList = scoreDao.getAllScoreRecord();
//        System.out.println("排序后");
//        for(ScoreRecord scoreRecord : recordList) {
//            System.out.println(scoreRecord);
//        }
//
//    }

}
