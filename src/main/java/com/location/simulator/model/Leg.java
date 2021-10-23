
package com.location.simulator.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Leg {

   
    private Distance distance;
    
    private Duration duration;
    @JsonProperty("end_address")
    private String endAddress;
    @JsonProperty("end_location")
    private EndLocation endLocation;
    @JsonProperty("start_address")
    private String startAddress;
    @JsonProperty("start_location")
    private StartLocation startLocation;
    private List<Step> steps;
    @JsonProperty("traffic_speed_entry")
    private List<Object> trafficSpeedEntry;
    @JsonProperty("via_waypoint")
    private List<Object> viaWaypoint;

}
