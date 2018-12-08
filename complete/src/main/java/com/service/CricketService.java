package com.service;

import com.model.Team;
import com.model.Row;
import com.model.ScoreCard;
import com.model.Template;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class CricketService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void parseInput(ScoreCard match) throws Exception
    {
        match.getRows().forEach(row->{
            if(row.getRunsScored()<0)
                row.setRunsScored(0);

            if(row.getRunsConceded()<0)
                row.setRunsConceded(0);

            if(row.getWickets()<0||row.getWickets()>4)
                row.setWickets(0);

            if(row.getCatches()<0||row.getCatches()>4)
                row.setCatches(0);

            if(row.getMaidens()<0||row.getMaidens()>2)
                row.setMaidens(0);

            if(row.getStumping()<0||row.getStumping()>4)
                row.setStumping(0);

            if(row.getRunOuts()<0||row.getRunOuts()>4)
                    row.setRunOuts(0);

            if(row.getOversBowled()<0||row.getOversBowled()>2)
                row.setOversBowled(0);
        });
    }


    /**
     * MOTM tie breaker based on name currently as did not get clarification from panel
     */
    private class ScoreComparator implements Comparator<Row>
    {
        public int compare(Row row1, Row row2)
        {
            Integer j1 = row1.getNetPlayerScore();
            Integer j2 = row2.getNetPlayerScore();

            if(j1==j2)
            {
                return row2.getName().compareTo(row1.getName());
            }

            if(j1>j2)
                return -1;

            return 1;
        }
    }



    /**
     * Assuming 4/40 is better than 3/1
     * @param match
     * @return
     * @throws Exception
     */
    public void determineBowledBest (ScoreCard match) throws Exception {
        Row bestBowler = new Row();
        bestBowler.setRunsConceded(100000);
        bestBowler.setWickets(0);

        for (Row row : match.getRows()) {
            if (row.getWickets() > bestBowler.getWickets())
                bestBowler = row;
            else if(row.getWickets()==bestBowler.getWickets()&&row.getRunsConceded()<bestBowler.getRunsConceded())
                    bestBowler = row;
        }
        match.setBestBowler(bestBowler);
    }

    public void determineBattedBest (ScoreCard match) throws Exception {
        Row bestBatter = new Row();
        bestBatter.setRunsScored(0);
        for (Row row : match.getRows()) {
            if (row.getRunsScored() > bestBatter.getRunsScored())
                bestBatter = row;
        }
        match.setBestBatter(bestBatter);
    }

    public void determinePlayerScores (ScoreCard match) throws Exception
    {
        match.getRows().forEach(row->{
            Integer netPlayerScore = 0;
            netPlayerScore+=(row.getRunsScored()>=20)?15:0;
            netPlayerScore+=(row.getRunsScored()/10)*2;
            netPlayerScore+=row.getMaidens()*2;
            netPlayerScore+=(row.getWickets()>=2)?20:0;
            netPlayerScore+=row.getWickets()*5;
            netPlayerScore+=row.getCatches()*2;
            netPlayerScore+=row.getRunOuts()*2;
            netPlayerScore+=row.getStumping()*2;
            row.setNetPlayerScore(netPlayerScore);
        });
    }

    /**
     *
     * @param match
     * @throws Exception
     */
    public void determineMatchWinner (ScoreCard match) throws Exception{

        match.getRows().forEach(row->{
            if(row.getTeam().equals(Team.Australia))
            {
                match.ausScore+= row.getRunsScored();
                match.indiaWickets+= row.getWickets();
            }
            else
            {
                match.indiaScore+=row.getRunsScored();
                match.ausWickets+=row.getWickets();
            }
        });

        if(match.ausScore>match.indiaScore)
        {
            match.setMatchWinner(Team.Australia);
        }
        else if(match.ausScore==match.indiaScore)
        {
            match.setMatchWinner(Team.Tie);
        }
        else
            match.setMatchWinner(Team.India);

    }

    /**
     *
     * @param match
     * @throws Exception
     */
    public void determineManOfTheMatch (ScoreCard match) throws Exception
    {   Row manOfTheMAtch = new Row();
        manOfTheMAtch.setNetPlayerScore(0);
//        ScoreComparator scoreComparator = new ScoreComparator();
        if(match.getMatchWinner().equals(Team.Tie))
        {
            match.setManOfTheMatch(null);
        }
        else
        {
//            List<Row> rowList = match.getRows();
//            rowList.sort(scoreComparator);
//
////            List<Row> resultList = new ArrayList<>();

            for (Row row : match.getRows())
            {
                if(row.getTeam().equals(match.getMatchWinner()))
                {   Integer motm = manOfTheMAtch.getNetPlayerScore(), player = row.getNetPlayerScore();

                    if(motm<player)
                        manOfTheMAtch  = row;
                }

            }
            match.setManOfTheMatch(manOfTheMAtch);
        }
        /**
         * TODO: add+10 to motm player
         */

    }

    public void setTemplateAndSummary(ScoreCard match) throws Exception
    {
        Team batFirst = match.getRows().get(0).getTeam();
        if(match.getMatchWinner().equals(Team.Tie))
        {
            match.setTemplate(Template.Tie);
        }
        else if(match.getMatchWinner().equals(batFirst))
        {
            match.setTemplate(Template.BatFirst);
        }
        else
            match.setTemplate(Template.BatSecond);


        String matchSummary = "";

        if(match.getTemplate().equals(Template.BatFirst))
        {
            matchSummary = new StringBuilder(match.getMatchWinner().toString()).append(" ")
                            .append("batting first, won the game by ")
                            .append(Math.abs(match.ausScore - match.indiaScore))
                            .append(" runs!! ")
                            .append(match.getManOfTheMatch().getName())
                            .append(" was declared man of the match. ")
                            .append(match.getBestBatter().getName())
                            .append(" was the highest run scorer and ")
                            .append(match.getBestBowler().getName())
                            .append(" bowled the best")
                            .toString();
        }
        else if(match.getTemplate().equals(Template.BatSecond))
        {
            matchSummary = new StringBuilder(match.getMatchWinner().toString()).append(" ")
                    .append("chased successfully and won the game by ")
                    .append(Math.abs(match.ausWickets - match.indiaWickets))
                    .append(" wickets!! ")
                    .append(match.getManOfTheMatch().getName())
                    .append(" was declared man of the match. ")
                    .append(match.getBestBatter().getName())
                    .append(" was the highest run scorer and ")
                    .append(match.getBestBowler().getName())
                    .append(" bowled the best")
                    .toString();
        }
        else
        {
            matchSummary = new StringBuilder("Match between India and Australia ended in a tie.").append(" ")
                    .append(match.getBestBatter().getName())
                    .append(" was the highest run scorer and ")
                    .append(match.getBestBowler().getName())
                    .append(" bowled the best")
                    .toString();
        }

        match.setMatchSummary(matchSummary);
    }


}
