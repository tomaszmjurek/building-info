package gryffindor.buildinginfo.gui;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import gryffindor.buildinginfo.models.Building;
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
        
    	
        Parent root = FXMLLoader.load(getClass().getResource("MenuWindow.fxml"));
        primaryStage.setTitle("Building-info!");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
        
    }
    
}