package com.location.simulator.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.location.simulator.constants.ApiConstants;
import com.location.simulator.model.GeoLocationResponse;
import com.location.simulator.model.LatLngsModel;
import com.location.simulator.model.LocationSimulatorRequestModel;
import com.location.simulator.model.LocationSimulatorResponseModel;
import com.location.simulator.utils.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class LocationSimulatorService {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  PropertiesUtil propertiesUtil;


  public Object getLocationDetails(LocationSimulatorRequestModel locationSimulatorRequestModel) {
    String destination = locationSimulatorRequestModel.getDestination().getLats() + "," + locationSimulatorRequestModel.getDestination().getLangs();
    String origin = locationSimulatorRequestModel.getOrigin().getLats() + "," + locationSimulatorRequestModel.getOrigin().getLangs();
    String Key = ApiConstants.API_KEY;
    final HashMap<String, String> pathVariables = new HashMap<>();
    pathVariables.put(ApiConstants.DESTINATION, destination);
    pathVariables.put(ApiConstants.KEY, Key);
    pathVariables.put(ApiConstants.ORIGIN, origin);
    String url = propertiesUtil.getGeoLocationBaseApi().buildAndExpand(pathVariables).toUriString();
    Object object = new Object();
    LocationSimulatorResponseModel locationSimulatorResponseModel = new LocationSimulatorResponseModel();
    try {
      object = restTemplate.getForObject(url, Object.class);
      Gson gson = new Gson();
      String json = gson.toJson(object);
      JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);
//      JsonElement routes= convertedObject.get("routes");//get(0).get("legs").get(0).get("distance").get("text");
//      String x = "";
//      List<Object> routeList = routes.get(0);
//      JsonElement lat = position.get("lat");
//      JsonElement lng = position.get("lng");
//
//      latLongList.add(lat.getAsDouble());
//      latLongList.add(lng.getAsDouble());
      double azimuth = propertiesUtil.calculateBearing(locationSimulatorRequestModel);
      //System.out.println(azimuth);
      ArrayList<LatLngsModel> cords = propertiesUtil.getLocations(50, azimuth, locationSimulatorRequestModel.getOrigin(), locationSimulatorRequestModel.getDestination());
      locationSimulatorResponseModel.setLatLngsModelList(cords);

    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return object;
  }
}




