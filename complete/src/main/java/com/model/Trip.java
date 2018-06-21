package com.model;

/**
 * Created by z002qz1 on 6/20/18.
 */

import lombok.Data;

@Data
public class Trip
{
    private String driverId;
    private String customerId;
    private Integer driverRating;
    private Integer customerRating;
    private String tripId;

}
