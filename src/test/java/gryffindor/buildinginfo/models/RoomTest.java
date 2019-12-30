package gryffindor.buildinginfo.models;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    Room room;

    @BeforeEach
    void setup(){
        room = new Room(1 , "Room",
                    new Float(10),
                    new Float(25),
                    new Float(10),
                    new Float(50));
    }

    @Test
    void readJSON() {
        JSONObject json = new JSONObject();
        Room test_room = null;
        try {
            json.put("id", 2);
            json.put("name", "Test room");
            json.put("area", new Float(10));
            json.put("volume", new Float(25));
            json.put("heating", new Float(10));
            json.put("light", new Float(50));

            test_room = Room.readJSON(json);
        }
        catch(Exception e){
            e.getCause();
            fail();
        }

        assertEquals(2, test_room.getId());
        assertEquals("Test room", test_room.getName());
        assertEquals(new Float(10), test_room.getArea());
        assertEquals(new Float(25), test_room.getVolume());
        assertEquals(new Float(10), test_room.getHeating());
        assertEquals(new Float(50), test_room.getLight());

    }

    @Test
    void avgHeating() {
        assertEquals(new Float("0.4"), this.room.avgHeating());
    }

    @Test
    void avgLight() {
        assertEquals(new Float("5.0"), this.room.avgLight());
    }
}