package gryffindor.buildinginfo.models;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class FloorTest {

    Floor floor;

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

        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(room1); rooms.add(room2);

        floor = new Floor(3, "Pieterko", rooms);
    }


    @Test
    void readJSON() {
        JSONObject json_floor = new JSONObject();
        JSONObject json_room = new JSONObject();
        JSONArray json_rooms = new JSONArray();

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
            floor = Floor.readJSON(json_floor);

        } catch(Exception e) {
            e.getCause();
            fail();
        }

        assertEquals(10, floor.getId());
        assertEquals(1, floor.getRooms().size());

    }

    @Test
    void getArea(){
        assertEquals(new Float(30), this.floor.getArea());
    }

    @Test
    void getVolume(){
        assertEquals(new Float(65), this.floor.getVolume());
    }

    @Test
    void avgHeating() {
        assertEquals(new Float(40.0/65.0), this.floor.avgHeating());
    }

    @Test
    void avgLight() {
        assertEquals(new Float(110.0/30.0), this.floor.avgLight());
    }
}