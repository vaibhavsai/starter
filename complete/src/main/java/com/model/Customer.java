package com.model;

import lombok.Data;

import java.util.List;

/**
 * Created by z002qz1 on 6/20/18.
 */
@Data
public class Customer
{

    private String customerId;

    private PersonalInfo personalInfo;

    private List<String> tripIds;

    private Double avgRating;
}
