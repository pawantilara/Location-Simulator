package com.location.simulator.service;

import com.location.simulator.constants.ApiConstants;
import com.location.simulator.model.GeoLocationResponse;
import com.location.simulator.model.LocationSimulatorRequestModel;
import com.location.simulator.model.Route;
import com.location.simulator.utils.PropertiesUtil;
import java.util.HashMap;
import java.util.LinkedHashMap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class LocationSimulatorService {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  PropertiesUtil propertiesUtil;

  public Object getLocationDetails(LocationSimulatorRequestModel locationSimulatorRequestModel){
    String destination = locationSimulatorRequestModel.getDestination().getLats()+","+locationSimulatorRequestModel.getDestination().getLangs();
    String origin = locationSimulatorRequestModel.getOrigin().getLats()+","+locationSimulatorRequestModel.getOrigin().getLangs();
    String Key = ApiConstants.API_KEY;
    final HashMap<String, String> pathVariables = new HashMap<>();
    pathVariables.put(ApiConstants.DESTINATION, destination);
    pathVariables.put(ApiConstants.KEY, Key);
    pathVariables.put(ApiConstants.ORIGIN, origin);
    String url = propertiesUtil.getGeoLocationBaseApi().buildAndExpand(pathVariables).toUriString();
    Object object  = new Object();
    GeoLocationResponse geoLocationResponse = new GeoLocationResponse();
    try {
       object= restTemplate.getForObject(url, Object.class);
       geoLocationResponse.setRoutes((Route[]) ((LinkedHashMap)object).get("routes"));
    }catch (Exception e) {
      log.error(e.getMessage());
    }
    return object;
  }
}
