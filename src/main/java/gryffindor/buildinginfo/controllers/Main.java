package gryffindor.buildinginfo.controllers;

import gryffindor.buildinginfo.models.Building;
import gryffindor.buildinginfo.models.JSONToBuildingParser;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller class
 * It enables web service communication
 */
@RestController
public class Main {

    @RequestMapping("/healthcheck")
    public Map<String, Object> healthcheck(){
        Map<String, Object> response = new HashMap<>();
        response.put("status", "ok");

        return response;
    }

	/**
	 * Example Request Json Structure
	 * 
	 * {
    	"buildings": [
        {
            "id": 11,
            "floors": [
                {
                    "id": 21,
					"rooms": [
						{
							"id": 31,
							"area": 2.0,
							"volume": 6.0,
							"heating": 4.0,
							"light": 5.0
						}
					]
                },
                {
                    "id": 22,
					"rooms": [
						{
							"id": 32,
							"area": 42.0,
							"volume": 336.0,
							"heating": 9.0,
							"light": 10.0
						}
					]
                }
            ]
        },

        {
            "id": 12,
            "floors": [
                {
                    "id": 23,
					"rooms": [
						{
							"id": 33,
							"area": 2.0,
							"volume": 6.0,
							"heating": 4.0,
							"light": 5.0
						}
					]
                },
                {
                    "id": 24,
					"rooms": [
						{
							"id": 34,
							"area": 42.0,
							"volume": 336.0,
							"heating": 9.0,
							"light": 10.0
						}
					]
                }
            ]
        }	
        
	]
	}
	 
	*
	* Example json response
	{
    	"area": 500
     }
	*
	*  URL: 127.0.0.1:8080/getArea
	*
	**/
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

	/**
	 * Example Request Json Structure
	 * 
	 * {
    	"buildings": [
        {
            "id": 11,
            "floors": [
                {
                    "id": 21,
					"rooms": [
						{
							"id": 31,
							"area": 2.0,
							"volume": 6.0,
							"heating": 4.0,
							"light": 5.0
						}
					]
                },
                {
                    "id": 22,
					"rooms": [
						{
							"id": 32,
							"area": 42.0,
							"volume": 336.0,
							"heating": 9.0,
							"light": 10.0
						}
					]
                }
            ]
        },

        {
            "id": 12,
            "floors": [
                {
                    "id": 23,
					"rooms": [
						{
							"id": 33,
							"area": 2.0,
							"volume": 6.0,
							"heating": 4.0,
							"light": 5.0
						}
					]
                },
                {
                    "id": 24,
					"rooms": [
						{
							"id": 34,
							"area": 42.0,
							"volume": 336.0,
							"heating": 9.0,
							"light": 10.0
						}
					]
                }
            ]
        }	
        
	]
	}
	 
	* 
	* Example json response
	*{
    	"light": 50
     }
	*
	* URL: 127.0.0.1:8080/avgLight
	*
	**/
    @PostMapping(value="/avgLight")
    public Map<String, Object> avgLight(@RequestBody String json ) {
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
                sum += building.avgLight();
            }

            response.put("light", sum);
        }

        return response;
    }
}
