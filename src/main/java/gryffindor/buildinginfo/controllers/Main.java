package gryffindor.buildinginfo.controllers;

import gryffindor.buildinginfo.models.Building;
import gryffindor.buildinginfo.models.JSONToBuildingParser;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
public class Main {

    @RequestMapping("/healthcheck")
    public Map<String, Object> healthcheck(){
        Map<String, Object> response = new HashMap<>();
        response.put("status", "ok");

        return response;
    }

    @PostMapping(value="/getArea")
    public Map<String, Object> getArea(@RequestBody String json ) {
        Map<String, Object> response = new HashMap<>();

        ArrayList<Building> buildings = null;
        try {
            buildings = JSONToBuildingParser.getBuildings(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(buildings == null) {
            response.put("error", "error");

        } else {
            Float sum = 0.0f;
            for(Building building : buildings) {
                sum += building.getArea();
            }

            response.put("area", sum);
        }

        return response;
    }
}
