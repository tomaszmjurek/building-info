package gryffindor.buildinginfo.models;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {

    Building building;
    Floor floor1;
    Floor floor2;

    @BeforeEach
    void setup(){
        Room room1 = new Room(1 , "Room1",
                new Float(10),
                new Float(25),
                new Float(10),
                new Float(50));
        Room room2 = new Room(2 , "Room2",
                new Float(20),
                new Float(40),
                new Float(30),
                new Float(60));
        Room room2 = new Room(3 , "Room3",
                new Float(10),
                new Float(20),
                new Float(30),
                new Float(20));

        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(room1); rooms.add(room2);
        
        ArrayList<Room> rooms2 = new ArrayList<>();
        rooms.add(room3);
        

        floor1 = new Floor(4, "Pietro1", rooms);
        floor2 = new Floor(5,"Pietro2", rooms2);
        
     
        ArrayList<Floor> floors = new ArrayList<>();
        floors.add(floor1); floors.add(floor2);
        
    }


    @Test
    void readJSON() {
        JSONObject json_building = new JSONObject();
        JSONArray json_floors = new JSONArray();
        JSONObject json_floor = new JSONObject();
        JSONObject json_room = new JSONObject();
        JSONArray json_rooms = new JSONArray();

        Building building = null;
        Floor floor = null;

        try {
            json_room.put("id", 2);
            json_room.put("name", "Test room");
            json_room.put("area", new Float(10));
            json_room.put("volume", new Float(25));
            json_room.put("heating", new Float(10));
            json_room.put("light", new Float(50));

            json_rooms.put(json_room);

            json_floor.put("id", 10);
            json_floor.put("name", "Testowe pieterko");
            json_floor.put("rooms", json_rooms);
            
            json_floors.put(json_floor);
            
            json_building.put("id",20);
            json_floor.put("name", "Testowy budyneczek");
            json_floor.put("floors", json_floors);
            
            building = Building.readJSON(json_building);

        } catch(Exception e) {
            e.getCause();
            fail();
        }

        assertEquals(20, building.getId());
        assertEquals(1, building.getFloors().size());

    }

    @Test
    void getArea(){
        assertEquals(new Float(40), this.building.getArea());
    }

    @Test
    void getVolume(){
        assertEquals(new Float(85), this.building.getVolume());
    }

    @Test
    void avgHeating() {
        assertEquals(new Float(70.0/85.0), this.building.avgHeating());
    }

    @Test
    void avgLight() {
        assertEquals(new Float(130.0/40.0), this.building.avgLight());
    }
}
