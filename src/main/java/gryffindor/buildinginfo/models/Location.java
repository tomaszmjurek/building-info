package gryffindor.buildinginfo.models;

public abstract class Location {

    private Integer id;
    private String name;

    public Location(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    abstract Float getArea();
    abstract Float getVolume();
    abstract Float avgHeating();
    abstract Float avgLight();

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
