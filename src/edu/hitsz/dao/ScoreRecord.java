package edu.hitsz.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Pockern
 */
public class ScoreRecord implements java.io.Serializable {

    private String playerName;
    private int score;
    private String dateNow;

    public ScoreRecord(String playerName, int score, String dateNow) {
        this.playerName = playerName;
        this.score = score;
        this.dateNow = dateNow;
    }

    @Override
    public String toString() {
        return playerName + ", " + score + ", " + dateNow;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDateNow() {
        return dateNow;
    }

    public void setDateNow(String dateNow) {
        this.dateNow = dateNow;
    }

    //    public static void main(String[] args) {
//        Date date = new Date();
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//        String dateNow;
//        dateNow = ft.format(date);
//        System.out.println("当前时间为: " + dateNow);
//    }
}
