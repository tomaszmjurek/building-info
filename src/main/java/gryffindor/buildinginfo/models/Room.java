package gryffindor.buildinginfo.models;

public class Room extends Location {

    private Float x;
    private Float y;
    private Float height;
    private Float heating;
    private Float light;


    public Float getArea(){
        return this.x * this.y;
    }

    public Float getVolume(){
        return this.x * this.y * this.height;
    }

    public Float avgHeating(){
        return this.heating / this.getArea();
    }

    public Float avgLight(){
        return this.light / this.getVolume();
    }

    public Room(Integer id, String name, Float x, Float y, Float height, Float heating, Float light){
        super(id, name);
        this.x = x;
        this.y = y;
        this.height = height;
        this.heating = heating;
        this.light = light;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getHeating() {
        return heating;
    }

    public void setHeating(Float heating) {
        this.heating = heating;
    }

    public Float getLumens() {
        return light;
    }

    public void setLumens(Float light) {
        this.light = light;
    }
}
