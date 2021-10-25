
package com.location.simulator.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {

 
    private Bounds bounds;
 
    private String copyrights;
 
    private List<Leg> legs;
    @JsonProperty("overview_polyline")
    private OverviewPolyline overviewPolyline;
 
    private String summary;
 
    private List<Object> warnings;
    @JsonProperty("waypoint_order")
    private List<Object> waypointOrder;
}
