package com.location.simulator.utils;

import com.location.simulator.model.LatLngsModel;
import com.location.simulator.model.LocationSimulatorRequestModel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;

@Configuration
@Component
@Getter
public class PropertiesUtil {
  @Value("${geo.location.api}")
  private String geoLocationApi;
  private static final long RADIUS_OF_EARTH = 6371000;
  public UriComponentsBuilder getGeoLocationBaseApi() {
    return UriComponentsBuilder.fromHttpUrl(geoLocationApi);
  }

  public static double calculateBearing(LocationSimulatorRequestModel locationSimulatorRequestModel) {
    double startLat = Math.toRadians(locationSimulatorRequestModel.getOrigin().getLats());
    double startLong = Math.toRadians(locationSimulatorRequestModel.getOrigin().getLangs());
    double endLat = Math.toRadians(locationSimulatorRequestModel.getDestination().getLats());
    double endLong = Math.toRadians(locationSimulatorRequestModel.getDestination().getLangs());
    double dLong = endLong - startLong;
    double dPhi = Math
            .log(Math.tan((endLat / 2.0) + (Math.PI / 4.0)) / Math.tan((startLat / 2.0) + (Math.PI / 4.0)));
    if (Math.abs(dLong) > Math.PI) {
      if (dLong > 0.0) {
        dLong = -(2.0 * Math.PI - dLong);
      } else {
        dLong = (2.0 * Math.PI + dLong);
      }
    }
    double bearing = (Math.toDegrees(Math.atan2(dLong, dPhi)) + 360.0) % 360.0;
    return bearing;
  }
  public static LatLngsModel getDestinationLatLng(double lat, double lng, double azimuth, double distance) {
    double radiusKm = RADIUS_OF_EARTH / 1000; // Radius of the Earth in km
    double brng = Math.toRadians(azimuth); // Bearing is degrees converted to radians.
    double d = distance / 1000; // Distance m converted to km
    double lat1 = Math.toRadians(lat); // Current dd lat point converted to radians
    double lon1 = Math.toRadians(lng); // Current dd long point converted to radians
    double lat2 = Math.asin(
            Math.sin(lat1) * Math.cos(d / radiusKm) + Math.cos(lat1) * Math.sin(d / radiusKm) * Math.cos(brng));
    double lon2 = lon1 + Math.atan2(Math.sin(brng) * Math.sin(d / radiusKm) * Math.cos(lat1),
            Math.cos(d / radiusKm) - Math.sin(lat1) * Math.sin(lat2));
    // convert back to degrees
    lat2 = Math.toDegrees(lat2);
    lon2 = Math.toDegrees(lon2);
    return new LatLngsModel(lat2, lon2);
  }

  public static ArrayList<LatLngsModel> getLocations(int interval, double azimuth, LatLngsModel start,
                                                     LatLngsModel end, double d) {


    int totalDistance = (int)d * 1000; // convert Km into m
    int coveredDist = interval;
    ArrayList<LatLngsModel> coords = new ArrayList<>();
    coords.add(new LatLngsModel(start.getLats(), start.getLangs()));
    for (int distance = 0; distance < totalDistance; distance += interval) {
      LatLngsModel coord = getDestinationLatLng(start.getLats(), start.getLangs(), azimuth, coveredDist);
      coveredDist += interval;
      coords.add(coord);
    }
    coords.add(new LatLngsModel(end.getLats(), end.getLangs()));

    return coords;

  }
}
