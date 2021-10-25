
package com.location.simulator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoLocationResponse {

    @JsonProperty("geocoded_waypoints")
    private String status;
    private GeocodedWaypoint[] geocodedWaypoints;
    private Route[] routes;


}
