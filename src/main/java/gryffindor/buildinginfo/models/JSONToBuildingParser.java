package gryffindor.buildinginfo.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class JSONToBuildingParser {
    public static ArrayList<Building> getBuildings(String json) throws JSONException {
        ArrayList<Building> parsedBuildings = new ArrayList<>();

        JSONArray buildings = (new JSONObject(new JSONTokener(json))).getJSONArray("buildings");

        for (int i = 0; i < buildings.length(); i++) {
            JSONObject building = buildings.getJSONObject(i);
            parsedBuildings.add(Building.readJSON(building));
        }

        return parsedBuildings;
    }
}
