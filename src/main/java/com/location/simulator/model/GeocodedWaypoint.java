
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
public class GeocodedWaypoint {

    @JsonProperty("geocoder_status")
    private String geocoderStatus;
    @JsonProperty("place_id")
    private String placeId;
    private List<String> types;
}
