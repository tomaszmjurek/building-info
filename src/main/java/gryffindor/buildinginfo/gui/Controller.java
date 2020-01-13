package gryffindor.buildinginfo.gui;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gryffindor.buildinginfo.models.Building;
import gryffindor.buildinginfo.models.Floor;
import gryffindor.buildinginfo.models.Room;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Controller implements Initializable{

	@FXML
	public Button newBuilding,editBuilding,deleteBuilding;
	@FXML
	public Button newFloor,editFloor,deleteFloor;
	@FXML
	public Button newRoom,editRoom,deleteRoom;
	@FXML
	public Button acceptBuilding, acceptFloor, acceptRoom;
	@FXML
	public Button acceptNewBuilding, acceptNewFloor, acceptNewRoom;
	@FXML
	public ListView<String> buildingList,floorList,roomList;
	@FXML
	public Label delete;
	@FXML
	public Label buildingName;
	@FXML
	public Label buildingID;
	@FXML
	public Label buildingHeating;
	@FXML
	public Label buildingLight;
	@FXML
	public Label floorName;
	@FXML
	public Label floorID;
	@FXML
	public Label floorHeating;
	@FXML
	public Label floorLight;
	@FXML
	public Label roomName;
	@FXML
	public Label roomID;
	@FXML
	public Label roomArea;
	@FXML
	public Label roomVolume;
	@FXML
	public Label roomHeating;
	@FXML
	public Label roomLight;
	@FXML
	public VBox buildingBox,floorBox,roomBox,deleteV;
	@FXML
	public VBox v1,v2,v3,v4,v5,v6;
	@FXML
	public Label q1,q2,q3,q4,q5,q6;
	@FXML
	public TextField a1,a2,a3,a4,a5,a6;
	@FXML
	public TextArea yaml;

	
	public ArrayList <VBox> fields = new ArrayList<>();
	public ArrayList <Button> buttons = new ArrayList<>();
	public int currentBuildingIndex;
	public int currentFloorIndex;
	public int currentRoomIndex;
	public Room deleteR = null;
	public Floor deleteF = null;
	public Building deleteB = null;
	
	
	@FXML
	public void yaml() {
		for(int i=0;i<fields.size();i++)
			fields.get(i).setVisible(false);
		for(int i=0;i<buttons.size();i++)
			buttons.get(i).setVisible(false);
		
		yaml.setVisible(true);
		yaml.setText(MenuWindow.buildings.get(currentBuildingIndex).convertToYAML());
	}
	@FXML
	public void newBuilding() {
		
		for(int i=0;i<fields.size();i++)
			fields.get(i).setVisible(false);
		yaml.setVisible(false);
		
		v1.setVisible(true);
		v2.setVisible(true);
		for(int i=0;i<buttons.size();i++)
			buttons.get(i).setVisible(false);
		acceptNewBuilding.setVisible(true);
		
		q1.setText("Set Building's Name");
		q2.setText("Set Building's ID");
		
		a1.setText("");
		a2.setText("");
	}
	@FXML
	public void newBuilding2() {
		v1.setVisible(false);
		v2.setVisible(false);
		
		acceptNewBuilding.setVisible(false);
		
		Building building = new Building(0,"",new ArrayList<>());
		
		building.setName(a1.getText());
		building.setId(Integer.parseInt(a2.getText()));
		
		MenuWindow.buildings.add(building);

		loadBuildings();
	}
	@FXML
	public void editBuilding() {
		
		for(int i=0;i<fields.size();i++)
			fields.get(i).setVisible(false);
		
		yaml.setVisible(false);
		
		v1.setVisible(true);
		v2.setVisible(true);
		
		q1.setText("Set Building's Name");
		q2.setText("Set Building's ID");
		
		a1.setText("");
		a2.setText("");
		
		a1.setPromptText(MenuWindow.buildings.get(currentBuildingIndex).getName());
		a2.setPromptText(Integer.toString(MenuWindow.buildings.get(currentBuildingIndex).getId()));
		
		for(int i=0;i<buttons.size();i++)
			buttons.get(i).setVisible(false);
		acceptBuilding.setVisible(true);
	}
	@FXML
	public void editBuilding2() {
		
		v1.setVisible(false);
		v2.setVisible(false);
		
		acceptBuilding.setVisible(false);
		
		if(!a1.getText().equals("")) MenuWindow.buildings.get(currentBuildingIndex).setName(a1.getText());
		if(!a2.getText().equals("")) MenuWindow.buildings.get(currentBuildingIndex).setId(Integer.parseInt(a2.getText()));
		
		loadBuildings();
	}
	@FXML
	public void deleteBuilding() {
		yaml.setVisible(false);
		deleteV.setVisible(true);
		deleteB = MenuWindow.buildings.get(currentBuildingIndex);
		delete.setText("Do you really want to delete current Building: "+deleteB.getName()+"?");
	}
	
	@FXML
	public void newFloor() {
		
		yaml.setVisible(false);
		for(int i=0;i<fields.size();i++)
			fields.get(i).setVisible(false);
		
		v1.setVisible(true);
		v2.setVisible(true);
		
		for(int i=0;i<buttons.size();i++)
			buttons.get(i).setVisible(false);
		acceptNewFloor.setVisible(true);
		
		q1.setText("Set Floor's Name");
		q2.setText("Set Floor's ID");
		
		a1.setText("");
		a2.setText("");
	}
	@FXML
	public void newFloor2() {
		v1.setVisible(false);
		v2.setVisible(false);
		
		acceptNewFloor.setVisible(false);
		
		Floor floor = new Floor(0,"",new ArrayList<>());
		
		floor.setName(a1.getText());
		floor.setId(Integer.parseInt(a2.getText()));

		MenuWindow.buildings.get(currentBuildingIndex).getFloors().add(floor);

		floorList.getItems().clear();
		loadFloors();
	}
	@FXML
	public void editFloor() {
		
		yaml.setVisible(false);
		for(int i=0;i<fields.size();i++)
			fields.get(i).setVisible(false);
		
		v1.setVisible(true);
		v2.setVisible(true);
		
		q1.setText("Set Floor's Name");
		q2.setText("Set Floor's ID");
		
		a1.setText("");
		a2.setText("");
		
		a1.setPromptText(MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getName());
		a2.setPromptText(Integer.toString(MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getId()));
		
		for(int i=0;i<buttons.size();i++)
			buttons.get(i).setVisible(false);
		acceptFloor.setVisible(true);
	}
	
	@FXML
	public void editFloor2() {

		
		v1.setVisible(false);
		v2.setVisible(false);
		
		acceptFloor.setVisible(false);
		
		if(!a1.getText().equals("")) MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).setName(a1.getText());
		if(!a2.getText().equals("")) MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).setId(Integer.parseInt(a2.getText()));
		
		loadFloors();
	}
	
	@FXML
	public void deleteFloor() {
		
		yaml.setVisible(false);
		deleteV.setVisible(true);
		deleteF = MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex);
		delete.setText("Do you really want to delete current Floor: "+deleteF.getName()+"?");
	}
	
	@FXML
	public void newRoom() {
		
		yaml.setVisible(false);
		for(int i=0;i<fields.size();i++)
			fields.get(i).setVisible(true);
		
		for(int i=0;i<buttons.size();i++)
			buttons.get(i).setVisible(false);
		acceptNewRoom.setVisible(true);
		
		q1.setText("Set Room's Name");
		q2.setText("Set Room's ID");
		q3.setText("Set Room's Light");
		q4.setText("Set Room's Heating");
		q5.setText("Set Room's Area");
		q6.setText("Set Room's Volume");
		
		a1.setText("");
		a2.setText("");
		a3.setText("");
		a4.setText("");
		a5.setText("");
		a6.setText("");
	}
	@FXML
	public void newRoom2() {
		
		for(int i=0;i<fields.size();i++)
			fields.get(i).setVisible(false);
		
		acceptNewRoom.setVisible(false);
		
		Room room = new Room(0 , "",
                new Float(0),
                new Float(0),
                new Float(0),
                new Float(0));
		room.setName(a1.getText());
		room.setId(Integer.parseInt(a2.getText()));
		room.setLight(Float.parseFloat(a3.getText()));
		room.setHeating(Float.parseFloat(a4.getText()));
		room.setArea(Float.parseFloat(a5.getText()));
		room.setVolume(Float.parseFloat(a6.getText()));
		
		MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().add(room);
		
		
		loadRooms();
	}
	@FXML
	public void editRoom() {
		
		yaml.setVisible(false);
		for(int i=0;i<fields.size();i++)
			fields.get(i).setVisible(true);
		
		for(int i=0;i<buttons.size();i++)
			buttons.get(i).setVisible(false);
		acceptRoom.setVisible(true);
		
		q1.setText("Set Room's Name");
		q2.setText("Set Room's ID");
		q3.setText("Set Room's Light");
		q4.setText("Set Room's Heating");
		q5.setText("Set Room's Area");
		q6.setText("Set Room's Volume");
		
		a1.setText("");
		a2.setText("");
		a3.setText("");
		a4.setText("");
		a5.setText("");
		a6.setText("");
		
		a1.setPromptText( MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(currentRoomIndex).getName());
		a2.setPromptText(Integer.toString( MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(currentRoomIndex).getId()));
		a3.setPromptText(Float.toString( MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(currentRoomIndex).getLight()));
		a4.setPromptText(Float.toString( MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(currentRoomIndex).getHeating()));
		a5.setPromptText(Float.toString( MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(currentRoomIndex).getArea()));
		a6.setPromptText(Float.toString( MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(currentRoomIndex).getVolume()));
	}
	@FXML
	public void editRoom2() throws IOException, ClassNotFoundException, NumberFormatException{
		for(int i=0;i<fields.size();i++)
			fields.get(i).setVisible(false);
		acceptRoom.setVisible(false);

		if(!a1.getText().equals("")) MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(currentRoomIndex).setName(a1.getText());
		if(!a2.getText().equals("")) MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(currentRoomIndex).setId(Integer.parseInt(a2.getText()));
		if(!a3.getText().equals("")) MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(currentRoomIndex).setLight(Float.parseFloat(a3.getText()));
		if(!a4.getText().equals("")) MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(currentRoomIndex).setHeating(Float.parseFloat(a4.getText()));
		if(!a5.getText().equals("")) MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(currentRoomIndex).setArea(Float.parseFloat(a5.getText()));
		if(!a6.getText().equals("")) MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(currentRoomIndex).setVolume(Float.parseFloat(a6.getText()));
		
		loadRooms();
	}
	@FXML
	public void deleteRoom() {
		
		yaml.setVisible(false);
		deleteV.setVisible(true);
		deleteR = MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(currentRoomIndex);
		delete.setText("Do you really want to delete current Room: "+deleteR.getName()+"?");
	}
	
	@FXML
	public void selectBuilding() {
		String name=(String) buildingList.getSelectionModel().getSelectedItem();
		Building building = new Building(0,"",new ArrayList<>());
		
		for (int i=0;i<MenuWindow.buildings.size();i++)
		{
			if (name.equals(MenuWindow.buildings.get(i).getName()+", id: "+MenuWindow.buildings.get(i).getId()))
            {
				building = MenuWindow.buildings.get(i);
				currentBuildingIndex = i;
            }
		}
		
		buildingName.setText("Name: "+building.getName());
		buildingID.setText("id: "+Integer.toString(building.getId()));
		buildingHeating.setText("Average Heating: "+Float.toString(building.avgHeating()));
		buildingLight.setText("Average Light: "+Float.toString(building.avgLight()));
		
		
		List <String> names = new ArrayList<>();
		
		for (int i=0;i<MenuWindow.buildings.get(currentBuildingIndex).getFloors().size();i++)
		{
			names.add(MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(i).getName()+", id: "+MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(i).getId());
		}
		
		floorList.getItems().clear();
		floorList.getItems().addAll(names);
		buildingBox.setVisible(true);
	}
	
	@FXML
	public void selectFloor() {
		String name=(String) floorList.getSelectionModel().getSelectedItem();
		Floor floor = new Floor(0,"",new ArrayList<>());
		
		for (int i=0;i<MenuWindow.buildings.get(currentBuildingIndex).getFloors().size();i++)
		{
			if (name.equals(MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(i).getName()+", id: "+MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(i).getId()))
            {
				floor = MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(i);
				currentFloorIndex = i;
            }
		}
		
		floorName.setText("Name: "+floor.getName());
		floorID.setText("id: "+Integer.toString(floor.getId()));
		floorHeating.setText("Average Heating: "+Float.toString(floor.avgHeating()));
		floorLight.setText("Average Light: "+Float.toString(floor.avgLight()));
		
		List <String> names = new ArrayList<>();
		
		for (int i=0;i<MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().size();i++)
		{
			names.add(MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(i).getName()+", id: "+MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(i).getId());
		}
		roomList.getItems().clear();
		roomList.getItems().addAll(names);
		floorBox.setVisible(true);
	}
	
	@FXML
	public void selectRoom() {
		String name=(String) roomList.getSelectionModel().getSelectedItem();
		Room room = new Room(0 , "",
                new Float(1),
                new Float(1),
                new Float(1),
                new Float(1));
		
		for (int i=0;i<MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().size();i++)
		{
			if (name.equals(MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(i).getName()+", id: "+MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(i).getId()))
            {
				room = MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(i);
				currentRoomIndex = i;
            }
		}
		
		roomName.setText("Name: "+room.getName());
		roomID.setText("id: "+Integer.toString(room.getId()));
		roomHeating.setText("Heating: "+Float.toString(room.getHeating()));
		roomLight.setText("Light: "+Float.toString(room.getLight()));
		roomVolume.setText("Volume: "+Float.toString(room.getVolume()));
		roomArea.setText("Area: "+Float.toString(room.getArea()));
		roomBox.setVisible(true);
	}
	
	public void loadBuildings() {
		List <String> names = new ArrayList<>();
		buildingList.getItems().clear();
		
		for (int i=0;i<MenuWindow.buildings.size();i++)
		{
			names.add(MenuWindow.buildings.get(i).getName()+", id: "+MenuWindow.buildings.get(i).getId());
		}
		
		buildingList.getItems().addAll(names);
		names.clear();
	}
	
	public void loadFloors() {
		List <String> names = new ArrayList<>();
		floorList.getItems().clear();
		
		for (int i=0;i<MenuWindow.buildings.get(currentBuildingIndex).getFloors().size();i++)
		{
			names.add(MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(i).getName()+", id: "+MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(i).getId());
		}
		
		floorList.getItems().addAll(names);
		names.clear();
	}
	
	public void loadRooms() {
		List <String> names = new ArrayList<>();
		roomList.getItems().clear();
		
		for (int i=0;i<MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().size();i++)
		{
			names.add(MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(i).getName()+", id: "+MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().get(i).getId());
		}
		
		roomList.getItems().addAll(names);
		names.clear();
	}
	
	@FXML
	public void save()throws IOException, ClassNotFoundException {
		
		
		
		ObjectOutputStream out = new ObjectOutputStream(        
                new FileOutputStream(("save.txt")));
		
		out.writeObject(MenuWindow.buildings);
		out.close();
	}
	
	
	@FXML
	public void deleteY() {
		if(deleteB!=null) {
			MenuWindow.buildings.remove(currentBuildingIndex); 
			loadBuildings();
			currentBuildingIndex = 0;
			buildingBox.setVisible(false);
			floorBox.setVisible(false);
			roomBox.setVisible(false);
		}
		else if(deleteF!=null) {
			MenuWindow.buildings.get(currentBuildingIndex).getFloors().remove(currentFloorIndex);
			loadFloors();
			currentFloorIndex = 0;
			floorBox.setVisible(false);
			roomBox.setVisible(false);
		}
		else if(deleteR!=null) {
			MenuWindow.buildings.get(currentBuildingIndex).getFloors().get(currentFloorIndex).getRooms().remove(currentRoomIndex);
			loadRooms();
			currentRoomIndex = 0;
			roomBox.setVisible(false);
		}
			
		
		deleteV.setVisible(false);
		deleteB = null;
		deleteR = null;
		deleteF = null;
		
		
	}
	
	@FXML
	public void deleteN() {
		
		deleteV.setVisible(false);
		deleteB = null;
		deleteR = null;
		deleteF = null;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
       loadBuildings();
       buttons.add(acceptBuilding);
       buttons.add(acceptNewBuilding);
       buttons.add(acceptFloor);
       buttons.add(acceptNewFloor);
       buttons.add(acceptRoom);
       buttons.add(acceptNewRoom);
       fields.add(v1);
       fields.add(v2);
       fields.add(v3);
       fields.add(v4);
       fields.add(v5);
       fields.add(v6);
    }    
	
}
