package gryffindor.buildinginfo.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class Building represents 1 of 3 locations used in our application
 * Building consists of floors
 * Building is described by its own unique id and name
 * @author Griffindor
 */
public class Building extends Location implements Serializable {
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

    /**
     * 
     * @return list of floors in a current building
     */
    public ArrayList<Floor> getFloors() {
        return floors;
    }
    
    public void setFloors(ArrayList<Floor> floors) {
        this.floors = floors;
    }

    /**
     * Function getArea() calculates area of a given building
     * It sums areas of every floor that our building consists of
     * @return area of building as float
     */
    @Override
    public Float getArea() {
        Float sum = 0.0f;
        for(Floor floor : floors) {
            sum += floor.getArea();
        }

        return sum;
    }

    /**
     * Function getVolume() calculates area of a given building
     * It sums volumes of every floor that our building consists of
     * @return volume of building as float
     */
    @Override
    public Float getVolume() {
        Float sum = 0.0f;
        for(Floor floor : floors) {
            sum += floor.getVolume();
        }

        return sum;
    }

    /**
     * Function avgHeating() calculates the amount of energy used to heat the whole building
     * It sums amounts of enery used to heat every floor that our building consists of and divides it by total area of a building
     * @return heating energy per m^3 as float
     */
    @Override
    public Float avgHeating() {
        return this.getHeating() / this.getVolume();
    }

    /**
     * Function avgLight() calculates the total lighting power used in the whole building
     * It sums amounts of power used to light every floor that our building consists of and divides it by total area of a building
     * @return total lighting power per m^2 as float
     */
    @Override
    public Float avgLight() {
        return this.getLight() / this.getArea();
    }

    /**
     * A function that sums the total amount of energy used to heat the whole building
     * @return heating energy
     */
    @Override
    public Float getHeating(){
        Float sum = 0.0f;
        for(Floor floor : this.floors){
            sum += floor.getHeating();
        }
        return sum;
    }

    /**
     * A function that sums the total amount of light used to light up the building
     * @return amount of light
     */
    @Override
    public Float getLight(){
        Float sum = 0.0f;
        for(Floor floor : this.floors){
            sum += floor.getLight();
        }
        return sum;
    }

    /**
     * A function that creates a yaml for the building
     * @return yaml
     */
    public String convertToYAML(){
        String yaml = "";
        for(Floor floor : this.floors){
            yaml += "- " + floor.getId() + ":\n";
            yaml += "\tname: " + floor.getName() + "\n";
            yaml += "\trooms:\n";
            for(Room room : floor.getRooms()){
                yaml += "\t\t- " + room.getId() + ":\n";
                yaml += "\t\t\tname: " + room.getName() + "\n";
                yaml += "\t\t\tarea: " + room.getArea() + "\n";
                yaml += "\t\t\tvolume: " + room.getVolume() + "\n";
                yaml += "\t\t\theating: " + room.getHeating() + "\n";
                yaml += "\t\t\tlight: " + room.getLight() + "\n";
            }
        }
        return yaml;
    }
}
