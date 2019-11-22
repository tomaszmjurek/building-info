package gryffindor.buildinginfo.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Building extends Location {
    public static Building readJSON(JSONObject json) throws JSONException {
        Integer id = (Integer) json.get("id");

        String name = null;
        if(json.has("name")) {
            name = (String) json.get("name");
        }

        JSONArray floors = json.getJSONArray("floors");
        ArrayList<Floor> parsedFloors = new ArrayList<>();

        for(int i=0; i < floors.length(); i++) {
            parsedFloors.add(Floor.readJSON(floors.getJSONObject(i)));
        }

        return new Building(id, name, parsedFloors);

    }

    private ArrayList<Floor> floors = new ArrayList<>();

    Building(Integer id, String name) {
        super(id, name);
    }

    public Building(Integer id, String name, ArrayList<Floor> floors) {
        this(id, name);
        this.floors = floors;
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    @Override
    public Float getArea() {
        Float sum = 0.0f;
        for(Floor floor : floors) {
            sum += floor.getArea();
        }

        return sum;
    }

    @Override
    public Float getVolume() {
        Float sum = 0.0f;
        for(Floor floor : floors) {
            sum += floor.getVolume();
        }

        return sum;
    }

    @Override
    public Float avgHeating() {
        Float sum = 0.0f;
        for(Floor floor : floors) {
            sum += floor.avgHeating();
        }

        return sum;
    }

    @Override
    public Float avgLight() {
        Float sum = 0.0f;
        for(Floor floor : floors) {
            sum += floor.avgLight();
        }

        return sum;
    }
}
