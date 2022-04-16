package edu.hitsz.dao;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

    /**
      *该类并非用于JUnit5测试，而是简单的熟悉函数功能使用
     */
public class DaoTest implements ScoreDao {

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String dateNow;
        dateNow = ft.format(date);

        try {
            ScoreRecord scoreRecord0 = new ScoreRecord("zhangsan", 10, dateNow);
            ScoreRecord scoreRecord1 = new ScoreRecord("zhangsan", 30, dateNow);
            ScoreRecord scoreRecord2 = new ScoreRecord("zhangsan", 20, dateNow);
            ScoreRecord scoreRecord3 = new ScoreRecord("zhangsan", 80, dateNow);
            ScoreRecord scoreRecord4 = new ScoreRecord("zhangsan", 5, dateNow);
            System.out.println(scoreRecord0);
            System.out.println(scoreRecord1);
            System.out.println(scoreRecord2);
            System.out.println(scoreRecord3);
            System.out.println(scoreRecord4);

            List<ScoreRecord> scoreRecordList = new LinkedList<>();
            scoreRecordList.add(scoreRecord0);
            scoreRecordList.add(scoreRecord1);
            scoreRecordList.add(0,scoreRecord2);
            scoreRecordList.add(scoreRecord3);
            scoreRecordList.add(scoreRecord4);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("database\\ScoreList.txt"));
            oos.writeObject(scoreRecordList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = new File("database\\ScoreList.txt");
        try {
            List<ScoreRecord> scoreRecordList2 = new LinkedList<>();

            if(file.length() > 0) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                scoreRecordList2 = (List<ScoreRecord>) ois.readObject();
            }
                System.out.println("反序列化后输出：");
                for(ScoreRecord scoreRecord : scoreRecordList2) {
                    System.out.println(scoreRecord);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ScoreRecord findByPlayerName(String playerName) {
        return null;
    }

    @Override
    public List<ScoreRecord> getAllScoreRecord() {
        return null;
    }

    @Override
    public void doAdd(ScoreRecord scoreRecord) {

    }

    @Override
    public void doDelete() {

    }
}
