package gryffindor.buildinginfo.gui;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import gryffindor.buildinginfo.models.Building;
import gryffindor.buildinginfo.models.Floor;
import gryffindor.buildinginfo.models.Room;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 


public class MenuWindow extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    public static List <Building> buildings = new ArrayList<>();
    
	@Override
    public void start(Stage primaryStage) throws Exception {
    	
		System.out.println(System.getProperty("user.dir"));
    	ObjectInputStream in = new ObjectInputStream( 
                new FileInputStream("save.txt"));
        
    	BufferedReader br = new BufferedReader(new FileReader(("save.txt")));     
        if (br.readLine() == null) {
        	in.close(); 
        }
        else {
        	buildings = (ArrayList<Building>) in.readObject();
        	in.close(); 
        }
        br.close();
        
        System.out.println(buildings.get(0).getFloors());
        System.out.println(buildings.get(1).getFloors());
    	
        Parent root = FXMLLoader.load(getClass().getResource("MenuWindow.fxml"));
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
        
    }
    
}