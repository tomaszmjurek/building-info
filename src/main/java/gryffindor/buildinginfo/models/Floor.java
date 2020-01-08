package gryffindor.buildinginfo.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Class Floor represents 1 of 3 locations used in our application
 * Floor consists of Rooms
 * Every Floor is described by its own unique id and name
 * @author Griffindor
 */
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

    /**
     * 
     * @return list of rooms in a current floor
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * Function getArea() calculates the total area of a floor
     * It sums areas of every room that the floor consists of
     * @return area of floor as float
     */
    @Override
    public Float getArea() {
        Float sum = 0.0f;

        for(Room room : rooms) {
            sum += room.getArea();
        }

        return sum;
    }
    
    /**
     * Function getVolume() calculates the total volume of a floor
     * It sums volumes of every room that the floor consists of
     * @return volume of floor as float
     */
    @Override
    public Float getVolume() {
        Float sum = 0.0f;

        for(Room room : rooms) {
            sum += room.getVolume();
        }

        return sum;
    }

    /**
     * Function avgHeating() calculates the level of heating energy consumption
     * It sums energy used to heat every room that the floor consists of and divides it by total volume of a floor
     * @return heating energy per m^3 as float
     */
    @Override
    public Float avgHeating() {
        Float sum = 0.0f;
        Float volume = 0.0f;

        for(Room room : rooms) {
            sum += room.getHeating();
            volume += room.getVolume();
        }

        return sum/volume;
    }
    
    /**
     * Function avgLight() calculates the total lighting power used in the whole floor
     * It sums amounts of power used to light every room that our floor consists of and divides it by total area of the floor
     * @return total lighting power per m^2 as float
     */
    @Override
    public Float avgLight() {
        Float sum = 0.0f;
        Float area = 0.0f;

        for(Room room : rooms) {
            sum += room.getLight();
            area += room.getArea();

        }

        return sum/area;
    }

    @Override
    public Float getHeating(){
        Float sum = 0.0f;
        for(Room room : this.rooms){
            sum += room.getHeating();
        }
        return sum;
    }

    @Override
    public Float getLight(){
        Float sum = 0.0f;
        for(Room room : this.rooms){
            sum += room.getLight();
        }
        return sum;
    }

}
