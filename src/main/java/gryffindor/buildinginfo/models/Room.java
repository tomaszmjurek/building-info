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

    private Float x;
    private Float y;
    private Float height;
    private Float heating;
    private Float light;

    public static Room readJSON(JSONObject json) throws JSONException {
        Integer id = (Integer) json.get("id");

        String name = null;
        if(json.has("name")) {
            name = (String) json.get("name");
        }
        System.out.println(json.get("x"));
        Float x = Float.valueOf(Double.toString((Double )json.get("x")));
        Float y = Float.valueOf(Double.toString((Double )json.get("y")));
        Float height = Float.valueOf(Double.toString((Double )json.get("height")));
        Float heating = Float.valueOf(Double.toString((Double )json.get("heating")));
        Float light = Float.valueOf(Double.toString((Double )json.get("light")));

        return new Room(id, name, x, y, height, heating, light);
    }

    /**
     * Function getArea() calculates area of a given room
     * It multiplies its length by its width
     * @return area of a room as float
     */
    public Float getArea(){
        return this.x * this.y;
    }

    /**
     * Function getVolume() calculates volume of a given room
     * It multiplies its length by its width by its height
     * @return volume of a room as float
     */
    public Float getVolume(){
        return this.x * this.y * this.height;
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

    public Room(Integer id, String name, Float x, Float y, Float height, Float heating, Float light){
        super(id, name);
        this.x = x;
        this.y = y;
        this.height = height;
        this.heating = heating;
        this.light = light;
    }

    /**
     * 
     * @return length of a room
     */
    public Float getX() {
        return x;
    }

    /**
     * 
     * @param x - length of the room
     */
    public void setX(Float x) {
        this.x = x;
    }

    /**
     * 
     * @return width of a room
     */
    public Float getY() {
        return y;
    }

    /**
     * 
     * @param y - width of the room
     */
    public void setY(Float y) {
        this.y = y;
    }

    /**
     * 
     * @return height of a room
     */
    public Float getHeight() {
        return height;
    }

    /**
     * 
     * @param height - height of a room
     */
    public void setHeight(Float height) {
        this.height = height;
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
