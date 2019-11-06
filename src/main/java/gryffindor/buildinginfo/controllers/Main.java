package gryffindor.buildinginfo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class Main {

    @RequestMapping("/healthcheck")
    public Map<String, Object> healthcheck(){
        Map<String, Object> response = new HashMap<>();
        response.put("status", "ok");

        return response;
    }

}
