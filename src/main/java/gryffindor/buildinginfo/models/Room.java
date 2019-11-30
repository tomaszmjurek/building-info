package gryffindor.buildinginfo.models;

import org.json.JSONException;
import org.json.JSONObject;

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
        System.out.println(json.get("x"));
        Float area = Float.valueOf(Double.toString((Double )json.get("area")));
        Float volume = Float.valueOf(Double.toString((Double )json.get("volume")));
        Float heating = Float.valueOf(Double.toString((Double )json.get("heating")));
        Float light = Float.valueOf(Double.toString((Double )json.get("light")));

        return new Room(id, name, area, volume, heating, light);
    }

    public Float getArea(){
        return this.area;
    }

    public Float getVolume(){
        return this.volume;
    }

    public Float avgHeating(){
        return this.heating / this.getArea();
    }

    public Float avgLight(){
        return this.light / this.getVolume();
    }

    public Room(Integer id, String name, Float area, Float volume, Float heating, Float light){
        super(id, name);
        this.area = area;
        this.volume = volume;
        this.heating = heating;
        this.light = light;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }
    
    public Float getHeating() {
        return heating;
    }

    public void setHeating(Float heating) {
        this.heating = heating;
    }

    public Float getLight() {
        return light;
    }

    public void setLight(Float light) {
        this.light = light;
    }
}
