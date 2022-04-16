package edu.hitsz.dao;

import java.util.List;

/**
 * @author Pockern
 */
public interface ScoreDao {

    ScoreRecord findByPlayerName(String playerName);

    List<ScoreRecord> getAllScoreRecord();

    void doAdd(ScoreRecord scoreRecord);

    void doDelete();
}
