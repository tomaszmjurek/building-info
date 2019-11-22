package gryffindor.buildinginfo.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Floor extends Location {
    public static Floor readJSON(JSONObject json) throws JSONException {
        Integer id = (Integer) json.get("id");

        String name = null;
        if(json.has("name")) {
            name = (String) json.get("name");
        }

        JSONArray rooms = json.getJSONArray("rooms");
        ArrayList<Room> parsedRooms = new ArrayList<>();

        for(int i=0; i < rooms.length(); i++) {
            parsedRooms.add(Room.readJSON(rooms.getJSONObject(i)));
        }

        return new Floor(id, name, parsedRooms);
    }

    private ArrayList<Room> rooms = new ArrayList<>();

    public Floor(Integer id, String name) {
        super(id, name);
    }

    public Floor(Integer id, String name, ArrayList<Room> rooms) {
        this(id, name);
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    @Override
    public Float getArea() {
        Float sum = 0.0f;

        for(Room room : rooms) {
            sum += room.getArea();
        }

        return sum;
    }

    @Override
    public Float getVolume() {
        Float sum = 0.0f;

        for(Room room : rooms) {
            sum += room.getVolume();
        }

        return sum;
    }

    @Override
    public Float avgHeating() {
        Float sum = 0.0f;

        for(Room room : rooms) {
            sum += room.getHeating();
        }

        return sum;
    }

    @Override
    public Float avgLight() {
        Float sum = 0.0f;

        for(Room room : rooms) {
            sum += room.avgLight();
        }

        return sum;
    }
}
