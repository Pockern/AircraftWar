package edu.hitsz.dao;

import java.util.List;

/**
 * @author Pockern
 */
public interface ScoreDao {

    /**
     * 按名字寻找对应数据记录
     * @param playerName 需要查找的用户名称
     * @return ScoreRecord 对应查找名称的对象
     */
    ScoreRecord findByPlayerName(String playerName);

    List<ScoreRecord> getAllScoreRecord();

    void doAdd(ScoreRecord scoreRecord);

    void doDelete();
}
