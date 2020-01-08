package gryffindor.buildinginfo.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
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

    private JSONObject json_building;

    @Before
    void setup() {
        JSONArray json_floors = new JSONArray();
        JSONObject json_floor = new JSONObject();
        JSONObject json_room = new JSONObject();
        JSONArray json_rooms = new JSONArray();
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

            this.json_building.put("id", 20);
            this.json_building.put("name", "Testowy budyneczek");
            this.json_building.put("floors", json_floors);

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

}