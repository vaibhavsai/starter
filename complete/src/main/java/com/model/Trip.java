package com.model;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

@Data
public class Trip
{
    @JsonProperty("driver")
    private String driverId;
    @JsonProperty("customer")
    private String customerId;
    @JsonProperty("driverRating")
    @Range(min = 0, max = 5, message = "rating has to be a value between 0 and 5")
    private Integer driverRating;
    @JsonProperty("customerRating")
    @Range(min = 0, max = 5, message = "rating has to be a value between 0 and 5")
    private Integer customerRating;
    private String tripId;

}
