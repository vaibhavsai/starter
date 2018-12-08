package com.model;

import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ScoreCard {
    List<Row> rows;
    String matchSummary;
    Template template;
    private Row bestBatter;
    private Row bestBowler;
    private Row manOfTheMatch;

    private Team matchWinner;
//    public Pair<Integer,Integer> indiaScore;
//    public Pair<Integer,Integer> ausScore;
    public Integer indiaScore;
    public Integer indiaWickets;
    public Integer ausScore;
    public Integer ausWickets;
    public Pair<Team,Team> teams;

    public ScoreCard(){
        indiaScore = 0;
        ausScore = 0;
        indiaWickets = 0;
        ausWickets = 0;
    }
}
