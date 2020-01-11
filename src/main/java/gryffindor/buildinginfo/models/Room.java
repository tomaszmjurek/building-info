package gryffindor.buildinginfo.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class Room represents 1 of 3 locations used in our application
 * Room is the smallest part of the Building
 * A set of rooms creates a floor
 * Every Room is described by its own unique id, name but also by length, width and height
 * Additionally every Room has a field heating is equal to energy used to heat the Room and 
 * a field light that is equal to power used to light the Room
 * @author Griffindor
 */
public class Room extends Location {

    private Float area;
    private Float volume;
    private Float heating;
    private Float light;

    public static Room readJSON(JSONObject json) throws JSONException {
        Integer id = (Integer) json.get("id");

        String name = null;
        if(json.has("name")) {
            name = (String) json.get("name");
        }
        
        Float area = new Float(json.get("area").toString());
        Float volume = new Float(json.get("volume").toString());
        Float heating = new Float(json.get("heating").toString());
        Float light = new Float(json.get("light").toString());

        return new Room(id, name, area, volume, heating, light);
    }

    /**
     * Function getArea() calculates area of a given room
     * It multiplies its length by its width
     * @return area of a room as float
     */
    public Float getArea(){
        return this.area;
    }

    /**
     * Function getVolume() calculates volume of a given room
     * It multiplies its length by its width by its height
     * @return volume of a room as float
     */
    public Float getVolume(){
        return this.volume;
    }

    /**
     * Function avgHeating() calculates average consumption of heating energy
     * It divides the amount of energy used to heat the Room by its volume
     * @return average consumption of heating energy on m^3
     */
    public Float avgHeating(){
        return this.heating / this.getVolume();
    }
    /**
     * Function avgLight() calculates average consumption of lighting power
     * It divides the amount of power used to light the Room by its area
     * @return average consumption of lighting power on m^2
     */
    public Float avgLight(){
        return this.light / this.getArea();
    }

    public Room(Integer id, String name, Float area, Float volume, Float heating, Float light){
        super(id, name);
        this.area = area;
        this.volume = volume;
        this.heating = heating;
        this.light = light;
    }

    /**
     *
     * @param area - room area in square meters
     */
    public void setArea(Float area) {
        this.area = area;
    }

    /**
     *
     * @param volume - room volume in cubic meters
     */
    public void setVolume(Float volume) {
        this.volume = volume;
    }
    
    /**
     * 
     * @return heating energy used by a room
     */
    public Float getHeating() {
        return heating;
    }

    /**
     * 
     * @param heating - heating energy used by a room
     */
    public void setHeating(Float heating) {
        this.heating = heating;
    }

    /**
     * 
     * @return lighting power used by a room
     */
    public Float getLight() {
        return light;
    }

    /**
     * 
     * @param light - lighting power used by a room
     */
    public void setLight(Float light) {
        this.light = light;
    }
}
