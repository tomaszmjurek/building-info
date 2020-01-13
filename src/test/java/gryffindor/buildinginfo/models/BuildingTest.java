package gryffindor.buildinginfo.models;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

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
        Room room3 = new Room(3 , "Room3",
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

        building = new Building(6, "Budyneczek", floors);
        
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
            json_building.put("name", "Testowy budyneczek");
            json_building.put("floors", json_floors);
            
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

    @Test
    void getAreaWithMock() {
        Floor mocked_floor1 = mock(Floor.class);
        Floor mocked_floor2 = mock(Floor.class);

        when(mocked_floor1.getArea()).thenReturn(new Float(1));
        when(mocked_floor2.getArea()).thenReturn(new Float(1));

        ArrayList<Floor> floors = new ArrayList<>(
                Arrays.asList(mocked_floor1, mocked_floor2)
        );

        Building test_building = new Building(1, "Test_building", floors);
        Float result = test_building.getArea();

        verify(mocked_floor1, times(1)).getArea();
        verify(mocked_floor2, times(1)).getArea();

        assertEquals(new Float(2), result);
    }

    @Test
    void getVolumeWithMock() {
        Floor mocked_floor1 = mock(Floor.class);
        Floor mocked_floor2 = mock(Floor.class);

        when(mocked_floor1.getVolume()).thenReturn(new Float(1));
        when(mocked_floor2.getVolume()).thenReturn(new Float(1));

        ArrayList<Floor> floors = new ArrayList<>(
                Arrays.asList(mocked_floor1, mocked_floor2)
        );

        Building test_building = new Building(1, "Test_building", floors);
        Float result = test_building.getVolume();

        verify(mocked_floor1, times(1)).getVolume();
        verify(mocked_floor2, times(1)).getVolume();

        assertEquals(new Float(2), result);
    }

    @Test
    void avgHeatingWithMock() {
        Floor mocked_floor1 = mock(Floor.class);
        Floor mocked_floor2 = mock(Floor.class);

        when(mocked_floor1.getVolume()).thenReturn(new Float(1));
        when(mocked_floor2.getVolume()).thenReturn(new Float(1));
        when(mocked_floor1.getHeating()).thenReturn(new Float(5));
        when(mocked_floor2.getHeating()).thenReturn(new Float(5));

        ArrayList<Floor> floors = new ArrayList<>(
                Arrays.asList(mocked_floor1, mocked_floor2)
        );

        Building test_building = new Building(1, "Test_building", floors);
        Float result = test_building.avgHeating();

        verify(mocked_floor1, times(1)).getVolume();
        verify(mocked_floor2, times(1)).getVolume();
        verify(mocked_floor1, times(1)).getHeating();
        verify(mocked_floor2, times(1)).getHeating();

        assertEquals(new Float(5), result);
    }

    @Test
    void avgLightWithMock() {
        Floor mocked_floor1 = mock(Floor.class);
        Floor mocked_floor2 = mock(Floor.class);

        when(mocked_floor1.getArea()).thenReturn(new Float(1));
        when(mocked_floor2.getArea()).thenReturn(new Float(1));
        when(mocked_floor1.getLight()).thenReturn(new Float(5));
        when(mocked_floor2.getLight()).thenReturn(new Float(5));

        ArrayList<Floor> floors = new ArrayList<>(
                Arrays.asList(mocked_floor1, mocked_floor2)
        );

        Building test_building = new Building(1, "Test_building", floors);
        Float result = test_building.avgLight();

        verify(mocked_floor1, times(1)).getArea();
        verify(mocked_floor2, times(1)).getArea();
        verify(mocked_floor1, times(1)).getLight();
        verify(mocked_floor2, times(1)).getLight();

        assertEquals(new Float(5), result);
    }

    @Test
    void getHeatingWithMock(){
        Floor mocked_floor1 = mock(Floor.class);
        Floor mocked_floor2 = mock(Floor.class);

        when(mocked_floor1.getHeating()).thenReturn(new Float(1));
        when(mocked_floor2.getHeating()).thenReturn(new Float(1));

        ArrayList<Floor> floors = new ArrayList<>(
                Arrays.asList(mocked_floor1, mocked_floor2)
        );

        Building test_building = new Building(1, "Test_building", floors);
        Float result = test_building.getHeating();

        verify(mocked_floor1, times(1)).getHeating();
        verify(mocked_floor2, times(1)).getHeating();

        assertEquals(new Float(2), result);
    }

    @Test
    void getLightWithMock(){
        Floor mocked_floor1 = mock(Floor.class);
        Floor mocked_floor2 = mock(Floor.class);

        when(mocked_floor1.getLight()).thenReturn(new Float(1));
        when(mocked_floor2.getLight()).thenReturn(new Float(1));

        ArrayList<Floor> floors = new ArrayList<>(
                Arrays.asList(mocked_floor1, mocked_floor2)
        );

        Building test_building = new Building(1, "Test_building", floors);
        Float result = test_building.getLight();

        verify(mocked_floor1, times(1)).getLight();
        verify(mocked_floor2, times(1)).getLight();

        assertEquals(new Float(2), result);
    }

    
}
