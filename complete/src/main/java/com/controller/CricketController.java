package com.controller;

import com.model.Response;
import com.model.Row;
import com.model.ScoreCard;
import com.model.Team;
import com.service.CricketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by z002qz1 on 6/23/18.
 */
@RestController
@Validated
@RequestMapping(value = "/cricket/", produces = MediaType.APPLICATION_JSON_VALUE)
public class CricketController
{
//    public static List<List<Job>> testCases = new ArrayList<>();
    public List<ScoreCard> testCases = new ArrayList<>();
    public ScoreCard match = new ScoreCard();
    @Autowired
    private CricketService cricketService;

    @GetMapping("summary")//@ResponseBody
    public ResponseEntity<Response> getSummary()/*(@Valid List<ScoreCard> testCase)*/ throws Exception
    {
        cricketService.parseInput(match);
        Response response = new Response();
        cricketService.determineBattedBest(match);

        cricketService.determineBowledBest(match);

        cricketService.determinePlayerScores(match);

        cricketService.determineMatchWinner(match);

        cricketService.determineManOfTheMatch(match);

        cricketService.setTemplateAndSummary(match);

//        return new ResponseEntity<>(match.getMatchSummary(),HttpStatus.OK);
        response.setMatchSummary(match.getMatchSummary());
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }


    @GetMapping("Details")//@ResponseBody
    public ResponseEntity<ScoreCard> getDetails()/*(@Valid List<ScoreCard> testCase)*/ throws Exception
    {

        Response response = new Response();
        cricketService.determineBattedBest(match);

        cricketService.determineBowledBest(match);

        cricketService.determinePlayerScores(match);

        cricketService.determineMatchWinner(match);

        cricketService.determineManOfTheMatch(match);

        cricketService.setTemplateAndSummary(match);

//        return new ResponseEntity<>(match.getMatchSummary(),HttpStatus.OK);
        response.setMatchSummary(match.getMatchSummary());
        return new ResponseEntity<ScoreCard>(match,HttpStatus.OK);
    }



    CricketController(){

        Row row1 = new Row("Virat","Batsman", Team.India,20,0,1,0,1,0,0,0,0);
        Row row2 = new Row("Rahane","Batsman",Team.India,25,0,0,0,0,0,0,0,0);
        Row row3 = new Row("Bumrah","Bowler",Team.India,0,2,1,0,0,0,2,20,0);
        Row row4 = new Row("Shami","Bowler",Team.India,5,0,1,0,0,1,2,6,0);
        Row row5 = new Row("Jadeja","AllRounder",Team.India,15,2,0,0,0,0,1,10,0);


        Row row6 = new Row("Finch","Batsman",Team.Australia,10,0,1,0,0,0,0,0,0);
        Row row7 = new Row("Khwaja","Batsman",Team.Australia,15,0,2,0,0,0,0,0,0);
        Row row8 = new Row("Starc","Bowler",Team.Australia,4,2,1,0,0,0,2,20,0);
        Row row9 = new Row("Lyon","Bowler",Team.Australia,5,1,0,0,0,0,2,25,0);
        Row row10 = new Row("Cummins","AllRounder",Team.Australia,2,1,0,0,0,0,1,20,0);

        List<Row> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        rows.add(row5);
        rows.add(row6);
        rows.add(row7);
        rows.add(row8);
        rows.add(row9);
        rows.add(row10);

        match.setRows(rows);

    }

}
