
package com.location.simulator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bounds {

    private Northeast northeast;
    private Southwest southwest;
}
