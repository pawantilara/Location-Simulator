
package com.location.simulator.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeoLocationApiResponse {

    private List<GeocodedWaypoint> geocodedWaypoints;
    private List<Route> routes;
    private String status;

}
