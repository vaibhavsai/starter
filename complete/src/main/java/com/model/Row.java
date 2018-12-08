package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Row {
    private String name;
    private String profile;
    private Team team;
    private Integer runsScored;
    private Integer wickets;
    private Integer catches;
    private Integer stumping;
    private Integer runOuts;
    private Integer maidens;
    private Integer oversBowled;
    private Integer runsConceded;
    private Integer netPlayerScore;
}
