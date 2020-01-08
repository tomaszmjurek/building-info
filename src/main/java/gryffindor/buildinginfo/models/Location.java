package gryffindor.buildinginfo.models;

/**
 * Location is an abstract class
 * Classes Building, Floor and Room inherit from Location
 * Location has 2 fields - id and name
 * @author Griffindor
 */
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
    abstract Float getHeating();
    abstract Float getLight();

    /**
     * 
     * @return id of a location
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @return name of a location
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param id - id of a location
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @param name - name of a location
     */
    public void setName(String name) {
        this.name = name;
    }
}
