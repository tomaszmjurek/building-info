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
        JSONArray json_rooms = new JSONArray();
        JSONObject json_building = new JSONObject();
        JSONArray json_buildings = new JSONArray();
        JSONObject json_final = new JSONObject();
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
    void getArea() throws Exception {
        this.mockMvc.perform(post("/getArea")
                .content(this.json.toString()))
                .andDo(print())
                .andExpect(content().json("{'area': 10.0}"));
    }

    @Test
    void getVolume() throws Exception {
        this.mockMvc.perform(post("/getVolume")
                .content(this.json.toString()))
                .andDo(print())
                .andExpect(content().json("{'volume': 25.0}"));
    }

    @Test
    void avgLight() throws Exception {
        this.mockMvc.perform(post("/avgLight")
                .content(this.json.toString()))
                .andDo(print())
                .andExpect(content().json("{'light': 5.0}"));
    }

    @Test
    void avgHeating() throws Exception {
        this.mockMvc.perform(post("/avgHeating")
                .content(this.json.toString()))
                .andDo(print())
                .andExpect(content().json("{'heating': 0.4}"));
    }
}