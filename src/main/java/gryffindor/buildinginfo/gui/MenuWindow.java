package gryffindor.buildinginfo.gui;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gryffindor.buildinginfo.models.Building;
import gryffindor.buildinginfo.models.Floor;
import gryffindor.buildinginfo.models.Room;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MenuWindow extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static List <Building> buildings = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        buildings = this.get_example_building();

        URL url = new File("src/main/java/gryffindor/buildinginfo/gui/MenuWindow.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Budynek-Informacja!");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }

    private ArrayList<Building> get_example_building(){

        Floor floor1;
        Floor floor2;
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
        rooms2.add(room3);


        floor1 = new Floor(4, "Pietro1", rooms);
        floor2 = new Floor(5,"Pietro2", rooms2);

        ArrayList<Floor> floors = new ArrayList<>();
        floors.add(floor1); floors.add(floor2);

        return new ArrayList<>(
                Arrays.asList(new Building(6, "Budyneczek", floors))
        );
    }

}