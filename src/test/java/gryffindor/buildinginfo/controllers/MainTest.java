package gryffindor.buildinginfo.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = Main.class)
class MainTest {

    @Autowired
    private Main controller;
    @Autowired
    private MockMvc mockMvc;

    private JSONObject json;

    @BeforeEach
    void setup() {
        JSONArray json_floors = new JSONArray();
        JSONObject json_floor = new JSONObject();
        JSONObject json_room = new JSONObject();
        JSONObject json_room2 = new JSONObject();
        JSONArray json_rooms = new JSONArray();
        JSONObject json_building = new JSONObject();
        JSONArray json_buildings = new JSONArray();
        JSONObject json_final = new JSONObject();
        try {
            json_room.put("id", 3);
            json_room.put("name", "Test room");
            json_room.put("area", new Float(10));
            json_room.put("volume", new Float(25));
            json_room.put("heating", new Float(25));
            json_room.put("light", new Float(60));

            json_room2.put("id", 4);
            json_room2.put("name", "Test room 2");
            json_room2.put("area", new Float(15));
            json_room2.put("volume", new Float(30));
            json_room2.put("heating", new Float(30));
            json_room2.put("light", new Float(40));

            json_rooms.put(json_room);
            json_rooms.put(json_room2);

            json_floor.put("id", 10);
            json_floor.put("name", "Testowe pieterko");
            json_floor.put("rooms", json_rooms);

            json_floors.put(json_floor);

            json_building.put("id", 20);
            json_building.put("name", "Testowy budyneczek");
            json_building.put("floors", json_floors);

            json_buildings.put(json_building);

            json_final.put("buildings", json_buildings);
            this.json = json_final;

        } catch (Exception e) {
            e.getCause();
            fail();
        }
    }


    @Test
    void contextloads(){
        assertNotNull(controller);
    }

    @Test
    void healthcheck() throws Exception{
        this.mockMvc.perform(get("/healthcheck"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'status':'ok'}"));
    }

    @Test
    void getAreaAll() throws Exception {
        this.mockMvc.perform(post("/getArea?id=0")
                .content(this.json.toString()))
                .andDo(print())
                .andExpect(content().json("{'area': 25.0}"));
    }

    @Test
    void getAreaFloor() throws Exception {
        this.mockMvc.perform(post("/getArea?id=10")
                .content(this.json.toString()))
                .andDo(print())
                .andExpect(content().json("{'area': 25.0}"));
    }

    @Test
    void getVolumeAll() throws Exception {
        this.mockMvc.perform(post("/getVolume?id=0")
                .content(this.json.toString()))
                .andDo(print())
                .andExpect(content().json("{'volume': 55.0}"));
    }

    @Test
    void getVolumeRoom() throws Exception {
        this.mockMvc.perform(post("/getVolume?id=3")
                .content(this.json.toString()))
                .andDo(print())
                .andExpect(content().json("{'volume': 25.0}"));
    }

    @Test
    void avgLightAll() throws Exception {
        this.mockMvc.perform(post("/avgLight?id=0")
                .content(this.json.toString()))
                .andDo(print())
                .andExpect(content().json("{'light': 4.0}"));
    }

    @Test
    void avgLightRoom() throws Exception {
        this.mockMvc.perform(post("/avgLight?id=3")
                .content(this.json.toString()))
                .andDo(print())
                .andExpect(content().json("{'light': 6.0}"));
    }

    @Test
    void avgHeatingBuilding() throws Exception {
        this.mockMvc.perform(post("/avgHeating?id=20")
                .content(this.json.toString()))
                .andDo(print())
                .andExpect(content().json("{'heating': 1.0}"));
    }

    @Test
    void avgHeatingRoom() throws Exception {
        this.mockMvc.perform(post("/avgHeating?id=4")
                .content(this.json.toString()))
                .andDo(print())
                .andExpect(content().json("{'heating': 1.0}"));
    }

    @Test
    void roomsExceedingHeating() throws Exception {
        this.json.put("threshhold", 5);

        this.mockMvc.perform(post("/rooms/exceedingHeating")
                .content(this.json.toString()))
                .andDo(print()).
                andExpect(content().json("{'20': [3, 4]}"));
    }
}