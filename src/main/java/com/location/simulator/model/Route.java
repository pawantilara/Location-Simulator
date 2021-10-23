
package com.location.simulator.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
