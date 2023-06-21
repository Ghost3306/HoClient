package com.example.client;

import com.jfoenix.controls.JFXDrawer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class DoctorHome implements Initializable {
    public TableView<Appoint> appointTab;
    public TableColumn<Appoint,String> name;
    public TableColumn<Appoint,String> age;
    public TableColumn<Appoint,String> phone;
    public Button back;
    public Label dname;
    public JFXDrawer drawer;
    public TableColumn<Appoint,Button> more;
    ObservableList<Appoint> appointList;
    Sender sender = new Sender();
    public TableView<Patient> patientTab;
    public TableColumn<Patient,String> pname;
    public TableColumn<Patient,String> page;
    public TableColumn<Patient,Button> pmore;
    public TableColumn<Patient, String> pphone;
    ObservableList<Patient> patientsList;
    Files files = new Files();
    public void setDoc(String str){
        dname.setText(str);
    }

    public void setPatientTable() throws IOException, ClassNotFoundException {

        JSONObject jsonData = new JSONObject();
        JSONObject jsonReturn = new JSONObject();
        jsonData.put("purpose", "getTable");
        jsonData.put("of", "patient");
        jsonReturn = sender.sendJSON(jsonData);
        JSONObject jsonFile = new JSONObject();
        jsonFile = files.readFile();
        System.out.println("Data From Files" + jsonFile);
        jsonData.put("from", jsonFile.get("email").toString());
        jsonData.clear();
        jsonData.put("purpose", "getTable");
        jsonData.put("of", "appointment");
        jsonData.put("from", jsonFile.get("username"));


        JSONObject jsonRetunrn1 = new JSONObject();
        jsonRetunrn1 = sender.sendJSON(jsonData);
        System.out.println(jsonReturn);
        System.out.println(jsonRetunrn1);
        patientsList = FXCollections.observableArrayList();
        appointList = FXCollections.observableArrayList();

        //
        Iterator<JSONObject> iterator = jsonReturn.values().iterator();
        while (iterator.hasNext()) {
            JSONObject jsonChildObject = iterator.next();
            Button btn = new Button("More");
            patientsList.add(new Patient(jsonChildObject.get("firstname").toString() + " " + jsonChildObject.get("lastname").toString(),
                    jsonChildObject.get("age").toString(), jsonChildObject.get("phone").toString(), btn));
            btn.setOnAction((ActionEvent event) -> {
                Stage stage = (Stage) btn.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("PatientData.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 1000, 600);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                PatientData patientData = fxmlLoader.getController();
                patientData.setLabelValue(
                        jsonChildObject.get("firstname").toString(), jsonChildObject.get("middlename").toString(), jsonChildObject.get("lastname").toString(), jsonChildObject.get("dob").toString(),
                        jsonChildObject.get("age").toString(), jsonChildObject.get("email").toString(), jsonChildObject.get("phone").toString(),
                        jsonChildObject.get("address").toString(), jsonChildObject.get("height").toString(),
                        jsonChildObject.get("weight").toString(), jsonChildObject.get("work").toString(), jsonChildObject.get("healthissue").toString(),
                        jsonChildObject.get("physicalissue").toString()
                );


                stage.setTitle("Patient Data");
                stage.setScene(scene);
                stage.show();


            });
        }


        Iterator<JSONObject> iteratorA = jsonRetunrn1.values().iterator();

        while (iteratorA.hasNext()) {


            Button btn = new Button("Edit");
            JSONObject jsonChildObject = iteratorA.next();
            appointList.add(new Appoint(jsonChildObject.get("Name").toString(), jsonChildObject.get("Phone").toString(),
                    jsonChildObject.get("Date").toString(), btn));


            JSONObject finalJsonReturn = jsonReturn;
            btn.setOnAction((ActionEvent event) -> {
                //String phone = jsonChildObject.get("phone").toString();
                System.out.println(jsonChildObject.get("Phone").toString());
                System.out.println(finalJsonReturn);
                Iterator<JSONObject> iteratorChild = finalJsonReturn.values().iterator();
                while (iteratorChild.hasNext()){
                    JSONObject jChild = new JSONObject();
                    jChild = iteratorChild.next();
                    System.out.println(jChild);
                    if(jChild.get("phone").toString().equals(jsonChildObject.get("Phone").toString())){
                        System.out.println("true");
                    }else {
                        System.out.println("false");
                    }
                }




            });


        }
    }


    public void setDoc() throws IOException, ClassNotFoundException {
        JSONObject jsonData = new JSONObject();
        jsonData = files.readFile();
        dname.setText(jsonData.get("username").toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            setDoc();
            setPatientTable();

        }catch (Exception e){
            e.printStackTrace();
        }
        pname.setCellValueFactory(new PropertyValueFactory<Patient,String >("name"));
        page.setCellValueFactory(new PropertyValueFactory<Patient,String >("age"));
        pphone.setCellValueFactory(new PropertyValueFactory<Patient,String >("phone"));
        pmore.setCellValueFactory(new PropertyValueFactory<Patient,Button >("button"));
        patientTab.setItems(patientsList);
        name.setCellValueFactory(new PropertyValueFactory<Appoint,String>("name"));
        age.setCellValueFactory(new PropertyValueFactory<Appoint,String>("age"));
        phone.setCellValueFactory(new PropertyValueFactory<Appoint,String>("phone"));
        more.setCellValueFactory(new PropertyValueFactory<Appoint,Button>("button"));
        appointTab.setItems(appointList);

    }

    public void backLogin() throws IOException {
        Pane box = FXMLLoader.load(getClass().getResource("Drawer.fxml"));
        drawer.setSidePane(box);


        if(drawer.isShown()) {
            drawer.close();
        }else {
            drawer.open();
        }
        for(Node node:box.getChildren()){
            if(node.getAccessibleText()!=null){
                node.addEventHandler(MouseEvent.MOUSE_CLICKED,(e)->{
                    switch (node.getAccessibleText()) {
                        case "logoout" ->{
                            Stage stage = (Stage) back.getScene().getWindow();
                            FXMLLoader fxmlLoader = new FXMLLoader(EditPatientData.class.getResource("Login.fxml"));
                            Scene scene = null;
                            try {
                                scene = new Scene(fxmlLoader.load(), 1000, 600);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            stage.setTitle("Login");
                            stage.setScene(scene);
                            stage.show();
                        }
                    }
                });
            }
        }

        /*
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

         */
    }
}
