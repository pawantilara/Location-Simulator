
package com.location.simulator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Step {

    
    private Distance distance;
    
    private Duration duration;
    @JsonProperty("end_location")
    private EndLocation endLocation;
    @JsonProperty("html_instructions")
    private String htmlInstructions;
    
    private Polyline polyline;
    @JsonProperty("start_location")
    private StartLocation startLocation;
    @JsonProperty("travel_mode")
    private String travelMode;

}
